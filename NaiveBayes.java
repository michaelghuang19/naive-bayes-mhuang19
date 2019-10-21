// Do not submit with package statements if you are using eclipse.
// Only use what is provided in the standard libraries.

import java.io.*;
import java.util.*;

public class NaiveBayes {

    // TODO: How do we want to represent fractions?
    public Map<String, int[]> spamWords;
    public Map<String, int[]> hamWords;
    public int[] spamFraction;
    public int[] hamFraction;

    /*
     * !! DO NOT CHANGE METHOD HEADER !!
     * If you change the method header here, our grading script won't
     * work and you will lose points!
     * 
     * Train your Naive Bayes Classifier based on the given training
     * ham and spam emails.
     *
     * Params:
     *      hams - email files labeled as 'ham'
     *      spams - email files labeled as 'spam'
     */
    public void train(File[] hams, File[] spams) throws IOException {
        this.spamWords = new HashMap<>();
        this.hamWords = new HashMap<>();

        int numSpam = spams.length;
        int numHam = hams.length;

        int[] defaultSpam = {1, numSpam + 2};
        int[] defaultHam = {1, numHam + 2};

        trainProbabilities(spams, this.spamWords, defaultSpam);
        trainProbabilities(hams, this.hamWords, defaultHam);

        this.spamFraction = new int[] {numSpam, numSpam + numHam};
        this.hamFraction = new int[] {numHam, numSpam + numHam};

        

    }

    private void trainProbabilities(File[] files, Map<String, int[]> map, int[] defaultFraction)
            throws IOException {
        for (File file : files) {
            Set<String> fileTokens = tokenSet(file);

            for (String token : fileTokens) {
                if (!map.containsKey(token)) {
                    map.put(token, defaultFraction);
                }

                map.get(token)[0]++;
            }
        }
    }

    /*
     * !! DO NOT CHANGE METHOD HEADER !!
     * If you change the method header here, our grading script won't
     * work and you will lose points!
     *
     * Classify the given unlabeled set of emails. Add each email to the correct
     * label set. SpamFilterMain.java would follow the format in
     * example_output.txt and output your result to stdout. Note the order
     * of the emails in the output does NOT matter.
     * 
     *
     * Params:
     *      emails - unlabeled email files to be classified
     *      spams  - set for spam emails that needs to be populated
     *      hams   - set for ham emails that needs to be populated
     */
    public void classify(File[] emails, Set<File> spams, Set<File> hams) throws IOException {
        // TODO: remove the exception and add your code here
        throw new UnsupportedOperationException("Not implemented.");
    }


    /*
     *  Helper Function:
     *  This function reads in a file and returns a set of all the tokens. 
     *  It ignores "Subject:" in the subject line.
     *  
     *  If the email had the following content:
     *  
     *  Subject: Get rid of your student loans
     *  Hi there ,
     *  If you work for us , we will give you money
     *  to repay your student loans . You will be 
     *  debt free !
     *  FakePerson_22393
     *  
     *  This function would return to you
     *  ['be', 'student', 'for', 'your', 'rid', 'we', 'of', 'free', 'you', 
     *   'us', 'Hi', 'give', '!', 'repay', 'will', 'loans', 'work', 
     *   'FakePerson_22393', ',', '.', 'money', 'Get', 'there', 'to', 'If', 
     *   'debt', 'You']
     */
    public static HashSet<String> tokenSet(File filename) throws IOException {
        HashSet<String> tokens = new HashSet<String>();
        Scanner filescan = new Scanner(filename);
        filescan.next(); // Ignoring "Subject"
        while(filescan.hasNextLine() && filescan.hasNext()) {
            tokens.add(filescan.next());
        }
        filescan.close();
        return tokens;
    }
}
