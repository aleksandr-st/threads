package local.Test.threads;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	new Thread(new HungryStudent()).start();
    	new Thread(new Kock()).start();
    }
}
