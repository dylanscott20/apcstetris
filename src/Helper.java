public class Helper {

    public static void waitTime(int t) {
        if (t > 0)
        {
            try
            {
                Thread.sleep(t);
            }
            catch(Exception e){}
        }
    }

}
