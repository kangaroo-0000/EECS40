package Dictionary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;


class InvalidWordError extends RuntimeException{
    public InvalidWordError(){
        super("The word entered to add/find/clear is not a word.");
    }
}
class WordNotFoundError extends RuntimeException{
    public WordNotFoundError(){
        super("The word is not found in our dictionary.");
    }
}
class InvalidFrequencyError extends RuntimeException{
    public InvalidFrequencyError(){
        super("The frequency entered to add is not valid.");
    }
}
class WordDuplicatedError extends RuntimeException{
    public WordDuplicatedError(){
        super("The word that would like to be added has existed in the dictionary.");
    }
}
class FileNotFoundError extends RuntimeException{
    public FileNotFoundError(){
        super("The file path provided does not exist when importing or exporting from a txt file.");
    }
}

/**
 *
 * @param word a word
 * @param frequency the frequency of the word
 */
record Word(String word, int frequency) {}

class Dictionary {
    //TODO printWordMap function
    public HashMap<Word, String> getWordMap() {
        return wordMap;
    }

    HashMap<Word, String> wordMap;

    /**
     * Abstraction of a Hashmap that consists of a Word instance and a String of meaning. Makes lookup easier.
     */
    Dictionary() {
        wordMap = new HashMap<>();
    }

    void printWordMap() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dictionary");
        frame.setContentPane(new DictionaryGUI().dictionary);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}


public class DictionaryGUI extends Dictionary{
    private JLabel EnterYourWordHereLabel;
    private JLabel WordLabel;
    public JTextField getTextFrequency() {
        return TextFrequency;
    }
    private JTextField TextFrequency;
    private JLabel FrequencyLabel;

    public JTextField getTextFreqWord1() {
        return TextFreqWord1;
    }
    private JTextField TextFreqWord1;

    public JTextField getTextFreqWord2() {
        return TextFreqWord2;
    }
    private JTextField TextFreqWord2;

    public JTextField getTextFreqWord3() {
        return TextFreqWord3;
    }
    private JTextField TextFreqWord3;
    public JButton FINDButton;
    public JButton ADDButton;
    public JButton IMPORTButton;
    public JButton EXPORTButton;

    public JTextField getTextFilePath() {
        return TextFilePath;
    }
    private JTextField TextFilePath;

    public JTextArea getTextArea() {
        return TextArea;
    }
    private JTextArea TextArea;
    public JButton CLEARButton;
    public JButton REMOVEButton;
    public JPanel dictionary;
    public JTextField getTextWord() {
        return TextWord;
    }
    private JTextField TextWord;
    private JLabel FreqWord1Label;
    private JLabel FreqWord2Label;
    private JLabel FreqWord3Label;
    private JLabel FilePathLabel;

    /**
     * GUI layout class. Extends the Dictionary class. Mainly serves as a connection between GUI signals and backend logic.
     */
    public DictionaryGUI(){
        super();
        FINDButton.addActionListener(new findBtnClicked(this));
        ADDButton.addActionListener(new addBtnClicked(this));
        IMPORTButton.addActionListener(new importBtnClicked(this));
        EXPORTButton.addActionListener(new exportBtnClicked(this));
        CLEARButton.addActionListener(new clearBtnClicked(this));
        REMOVEButton.addActionListener(new removeBtnClicked(this));
    }
}

class FrequencyComparator implements Comparator<Word>{
    /**
     * Overrides the comparator of Tree set so Words stored in the set are automatically sorted in terms of frequency.
     * Returns 1 in case of words with same frequency to avoid words being overridden.
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return
     */
    public int compare(Word o1, Word o2) {
        return o2.frequency() - o1.frequency() != 0 ? o2.frequency() - o1.frequency() : 1;
    }
}
abstract class BtnClicked implements ActionListener {
    DictionaryGUI d;

    /**
     * Abstraction of the action button being clicked.
     * @param d DictionaryGUI instance injection. For manipulation of some variables inside Dictionary.
     */
    BtnClicked(DictionaryGUI d) {
        this.d = d;
    }
    public abstract void actionPerformed(ActionEvent e);
}

class findBtnClicked extends BtnClicked {
    //TODO when word is empty String "", do not print.
    // try catch InvalidWordError, WordNotFoundError
    findBtnClicked(DictionaryGUI d) {super(d);}

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // check for InvalidWordError
            char[] words = d.getTextWord().getText().toCharArray();
            for (char word : words) {
                if (!(97 <= word && word <= 122 || 65 <= word && word <= 90))
                    throw new InvalidWordError();
            }
        } catch(InvalidWordError iwe) {
            d.getTextArea().setText(iwe.getMessage());
            throw new InvalidWordError();
        }

        // determine if the word is a prefix (then output frequent words) or an exact word (then output the exact meaning)
        String word = d.getTextWord().getText();
        TreeSet<Word> set = new TreeSet<>(new FrequencyComparator());
        boolean isInDict = false;

        for (Word key : d.getWordMap().keySet()) {
            if (key.word().equals(word)) {
                d.getTextArea().setText(d.getWordMap().get(key));
                return;
            } else if (key.word().startsWith(word)) {
                set.add(key);
                isInDict = true;
            }
        }
        try {
            // check for WordNotFoundError
            if(!isInDict) throw new WordNotFoundError();
        } catch(WordNotFoundError wfe) {
            d.getTextArea().setText(wfe.getMessage());
            throw new WordNotFoundError();
        }
        // logic to retrieve and display most frequent words that starts with the same prefix
        Word[] displayed_freq_words = new Word[3];
        StringBuilder displayed_definition = new StringBuilder();
        int count = 3;
        while(count != 0 && !set.isEmpty()){
            displayed_freq_words[3-count] = Objects.requireNonNull(set.pollFirst());
            count--;
        }
        for(int i = 0; i < 3-count; i++) {
            displayed_definition.append(d.getWordMap().get(displayed_freq_words[i]));
            displayed_definition.append("\n");
        }
        d.getTextArea().setText(String.valueOf(displayed_definition));
        if(count == 0) {
            d.getTextFreqWord1().setText(displayed_freq_words[0].word());
            d.getTextFreqWord2().setText(displayed_freq_words[1].word());
            d.getTextFreqWord3().setText(displayed_freq_words[2].word());
        }
        if(count == 1){
            d.getTextFreqWord1().setText(displayed_freq_words[0].word());
            d.getTextFreqWord2().setText(displayed_freq_words[1].word());
        }
        if(count == 2){
            d.getTextFreqWord1().setText(displayed_freq_words[0].word());
        }
    }
}

class addBtnClicked extends BtnClicked {
    // try catch InvalidWordError, InvalidFrequencyError, WordDuplicatedError
    addBtnClicked(DictionaryGUI d) {super(d);}

    @Override
    public void actionPerformed(ActionEvent e) {
        int freq;
        try {
            // check for InvalidWordError
            char[] words = d.getTextWord().getText().toCharArray();
            for (char word : words) {
                if (!(97 <= word && word <= 122 || 65 <= word && word <= 90))
                    throw new InvalidWordError();
            }
        } catch(InvalidWordError iwe) {
            d.getTextArea().setText(iwe.getMessage());
            throw new InvalidWordError();
        }
        try {
            // check if the frequency text field is filled in. If not, assign 1 to frequency.
            if (!d.getTextFrequency().getText().equals("")) {
                // check for InvalidFrequencyError
                freq = Integer.parseInt(d.getTextFrequency().getText());
                if (!(freq > 0)) throw new InvalidFrequencyError();
            } else{
                freq = 1;
            }
        } catch(InvalidFrequencyError ife){
            d.getTextArea().setText(ife.getMessage());
            throw new InvalidFrequencyError();
        }
        try {
            // check for WordDuplicatedError
            for (Word key : d.getWordMap().keySet()) {
                if (d.getTextWord().getText().equals(key.word())) throw new WordDuplicatedError();
            }
        } catch(WordDuplicatedError wde){
            d.getTextArea().setText(wde.getMessage());
            throw new WordDuplicatedError();
        }
        // add word, meaning, and frequency to dictionary
        d.getWordMap().put(new Word(d.getTextWord().getText(), freq), d.getTextArea().getText());
    }
}

class clearBtnClicked extends BtnClicked{
    clearBtnClicked(DictionaryGUI d){super(d);}

    @Override
    public void actionPerformed(ActionEvent e) {
        d.getTextFreqWord3().setText("");
        d.getTextFreqWord2().setText("");
        d.getTextFreqWord1().setText("");
        d.getTextArea().setText("");
        d.getTextWord().setText("");
        d.getTextFilePath().setText("");
        d.getTextFrequency().setText("");
    }
}

class exportBtnClicked extends BtnClicked{
    exportBtnClicked(DictionaryGUI d){super(d);}

    @Override
    public void actionPerformed(ActionEvent e) {
        FileWriter fw;
        File f = new File(d.getTextFilePath().getText());
        try {
            if (!f.isFile()) throw new FileNotFoundError();
        } catch(FileNotFoundError ffe) {
            d.getTextArea().setText(ffe.getMessage());
            throw new FileNotFoundError();
        }
        try {
            fw = new FileWriter(f);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        // outputting to output files by wrapping FileWriter in BufferedWriter
        BufferedWriter bw = new BufferedWriter(fw);
        int cnt = 0;
        for(Map.Entry<Word, String> entry : d.getWordMap().entrySet()){
            Word w = entry.getKey();
            String s = entry.getValue();
            try {
                if(cnt != 0) bw.newLine();
                bw.write(w.word());
                bw.newLine();
                bw.write(String.valueOf(w.frequency()));
                bw.newLine();
                bw.write(s);
                bw.newLine();
                cnt++;
            }catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        try {
            bw.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
class removeBtnClicked extends BtnClicked{
    removeBtnClicked(DictionaryGUI d){super(d);}
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean isInDict = false;
        String word = d.getTextWord().getText();

        for (Word key : d.getWordMap().keySet()) {
            if (key.word().equals(word)) {
                d.getWordMap().remove(key);
                isInDict = true;
                break;
            }
        }
        try{
            if(!isInDict) throw new WordNotFoundError();
        } catch(WordNotFoundError wfe){
            d.getTextArea().setText(wfe.getMessage());
            throw new WordNotFoundError();
        }
    }
}
class importBtnClicked extends BtnClicked{
    // try catch FileNotFoundError
    importBtnClicked(DictionaryGUI d){super(d);}

    @Override
    public void actionPerformed(ActionEvent e) {
        FileReader fr;
        String imported_string;

        File f = new File(d.getTextFilePath().getText());
        // check for FileNotFoundError
        try {
            if (!f.isFile()) throw new FileNotFoundError();
        } catch(FileNotFoundError ffe){
            d.getTextArea().setText(ffe.getMessage());
            throw new FileNotFoundError();
        }
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        // parsing the file in groups of three lines
        BufferedReader br = new BufferedReader(fr);
        int count = 4;
        int freq = 0;
        String word = "";
        String meaning = "";
        while (true) {
            try {
                if ((imported_string = br.readLine()) == null) {
                    d.getWordMap().put(new Word(word, freq), meaning);
                    break;
                }
                count--;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(count == 3) word += imported_string;
            if(count == 2) freq = Integer.parseInt(imported_string);
            if(count == 1) meaning += imported_string;
            if(count == 0){
                d.getWordMap().put(new Word(word, freq), meaning);
                count = 4;
                meaning = word = "";
            }
        }
        try {
            br.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
