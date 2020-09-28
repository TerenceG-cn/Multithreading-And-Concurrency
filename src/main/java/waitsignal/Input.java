package waitsignal;

public class Input implements Runnable {

    private Resource r;
    public Input(Resource r){
        this.r = r;
    }

    @Override
    public void run() {
        int count = 0 ;
        while(true){
            if(count == 0){
                r.set("Tom", "man");
            }else{
                r.set("Lily", "woman");
            }
            //在两个个数据之间进行切换。
            count = (count + 1)%2;
        }
    }

}