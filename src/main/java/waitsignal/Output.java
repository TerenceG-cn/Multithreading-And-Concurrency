package waitsignal;

public class Output implements Runnable {

    private Resource r ;
    public Output(Resource r ){
        this.r = r;
    }
    @Override
    public void run() {
        while(true){
            r.out();
        }

    }
}
