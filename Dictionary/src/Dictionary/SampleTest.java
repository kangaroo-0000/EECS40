package Dictionary;

public class SampleTest {
    public static void main(String[] args) {
        DictionaryGUI myDictionary = new DictionaryGUI();
        // Test for ADD
        // InvalidWordError
        myDictionary.getTextWord().setText("s1mple");
        myDictionary.getTextFrequency().setText("1");
        myDictionary.getTextArea().setText("Best AWPer");
        try {
            myDictionary.ADDButton.doClick();
        } catch (InvalidWordError ex) {
            System.out.println("InvalidWordError passed");
        }

        // InvalidFrequencyError
        myDictionary.getTextWord().setText("niko");
        myDictionary.getTextFrequency().setText("-11");
        myDictionary.getTextArea().setText("Bad luck buddy");
        try {
            myDictionary.ADDButton.doClick();
        } catch (InvalidFrequencyError ex) {
            System.out.println("InvalidFrequencyError passed");
        }

        // WordDuplicatedError
        myDictionary.getTextWord().setText("niko");
        myDictionary.getTextFrequency().setText("11");
        myDictionary.getTextArea().setText("Fortunate entry fragger");
        myDictionary.ADDButton.doClick();

        myDictionary.getTextWord().setText("niko");
        myDictionary.getTextFrequency().setText("12");
        myDictionary.getTextArea().setText("Rifler");
        try {
            myDictionary.ADDButton.doClick();
        } catch (WordDuplicatedError ex) {
            System.out.println("WordDuplicatedError passed");
        }

        // Test for CLEAR button
        myDictionary = new DictionaryGUI();

        myDictionary.getTextWord().setText("niko");
        myDictionary.getTextFrequency().setText("11");
        myDictionary.getTextFreqWord1().setText("aaa");
        myDictionary.getTextFreqWord2().setText("bbb");
        myDictionary.getTextFreqWord3().setText("ccc");
        myDictionary.getTextArea().setText("Fortunate entry fragger");

        myDictionary.CLEARButton.doClick();
        String tmp0 = myDictionary.getTextWord().getText();
        String tmp1 = myDictionary.getTextFrequency().getText();
        String tmp2 = myDictionary.getTextFreqWord1().getText();
        String tmp3 = myDictionary.getTextFreqWord2().getText();
        String tmp4 = myDictionary.getTextFreqWord3().getText();
        String tmp5 = myDictionary.getTextArea().getText();

        if (tmp0.equals("") && tmp1.equals("") && tmp2.equals("") && tmp3.equals("") && tmp4.equals("") && tmp5.equals("")) {
            System.out.println("CLEAR button test passed");
        }

        // Test for REMOVE button
        myDictionary = new DictionaryGUI();

        myDictionary.getTextWord().setText("niko");
        myDictionary.getTextFrequency().setText("11");
        myDictionary.getTextArea().setText("Fortunate entry fragger");
        myDictionary.ADDButton.doClick();

        myDictionary.CLEARButton.doClick();
        // test for WordNotFoundError
        myDictionary.getTextWord().setText("NIKO");
        try {
            myDictionary.REMOVEButton.doClick();
        } catch (WordNotFoundError ex) {
            System.out.println("WordNotFoundError passed");
        }

        myDictionary.getTextWord().setText("niko");
        myDictionary.REMOVEButton.doClick(); // ok here
        try {
            myDictionary.REMOVEButton.doClick();
        } catch (WordNotFoundError ex) {
            System.out.println("WordNotFoundError passed");
        }

        // Test for FIND button
        myDictionary = new DictionaryGUI();

        myDictionary.getTextWord().setText("niko");
        myDictionary.getTextFrequency().setText("11");
        myDictionary.getTextArea().setText("Fortunate entry fragger");
        myDictionary.ADDButton.doClick();

        myDictionary.getTextWord().setText("nIko");
        myDictionary.getTextFrequency().setText("2");
        myDictionary.getTextArea().setText("Least Fortunate entry fragger");
        myDictionary.ADDButton.doClick();

        myDictionary.getTextWord().setText("nIKO");
        myDictionary.getTextFrequency().setText("7");
        myDictionary.getTextArea().setText("So-so Fortunate entry fragger");
        myDictionary.ADDButton.doClick();

        myDictionary.getTextWord().setText("niKo");
        myDictionary.getTextFrequency().setText("9");
        myDictionary.getTextArea().setText("Second Fortunate entry fragger");
        myDictionary.ADDButton.doClick();

        myDictionary.CLEARButton.doClick();

        myDictionary.getTextWord().setText("n");
        myDictionary.FINDButton.doClick();

        String textFreq1 = myDictionary.getTextFreqWord1().getText();
        String textFreq2 = myDictionary.getTextFreqWord2().getText();
        String textFreq3 = myDictionary.getTextFreqWord3().getText();

        if (textFreq1.equals("niko") && textFreq2.equals("niKo") && textFreq3.equals("nIKO")) {
            System.out.println("(partially) pass the FIND button check");
            System.out.println("Make sure to print out meanings of each word in TextArea");
        }

        // Test for IMPORT and EXPORT Button
        myDictionary = new DictionaryGUI();
        myDictionary.getTextFilePath().setText("input.txt"); // change your path to input.txt
        myDictionary.IMPORTButton.doClick();

        myDictionary.getTextFilePath().setText("output.txt"); // change your path to output.txt
        myDictionary.EXPORTButton.doClick();
        // compare output.txt with output_ref.txt
    }
}
