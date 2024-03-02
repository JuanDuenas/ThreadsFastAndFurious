import co.edu.uptc.model.Player;
import co.edu.uptc.model.ThreadPlayer;

public class Runner {
    public static void main(String[] args){
        Player p1 = new Player();
        p1.setThreadPlayer(new ThreadPlayer("America/Bogota"));

        Player p2 = new Player();
        p2.setThreadPlayer(new ThreadPlayer("Asia/Tokyo"));

        Player p3 = new Player();
        p3.setThreadPlayer(new ThreadPlayer("Europe/Paris"));

        Player p4 = new Player();
        p4.setThreadPlayer(new ThreadPlayer("America/New_York"));

        Player p5 = new Player();
        p5.setThreadPlayer(new ThreadPlayer("Europe/Rome"));

        Thread t1 = new Thread(p1.getThreadPlayer());
        Thread t2 = new Thread(p2.getThreadPlayer());
        Thread t3 = new Thread(p3.getThreadPlayer());
        Thread t4 = new Thread(p4.getThreadPlayer());
        Thread t5 = new Thread(p5.getThreadPlayer());

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
