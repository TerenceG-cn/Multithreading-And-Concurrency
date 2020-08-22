package Executor.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 串行地渲染页面元素p103 6-10
 */
public class SingleThreadRenader {
    void renderPage(CharSequence source){
        renderText(source);
        List<ImageData> imageData=new ArrayList<>();
        for(ImageInfo imageInfo:scanForImageInfo(source))
            imageData.add(imageInfo.downloadImage());
        for(ImageData data:imageData)
            renderImage(data);
    }

    private void renderImage(ImageData data) {
    }

    private ImageInfo[] scanForImageInfo(CharSequence source) {
        return new ImageInfo[1];//。。。
    }

    private void renderText(CharSequence source) {
    }

}
