package com.ufpor.app.client.view.custom;

import com.google.gwt.user.client.ui.SplitLayoutPanel;

/**
 * Created by Ehsan Barekati on 2/20/15.
 */
public class ResizingSplitLayoutPanel extends SplitLayoutPanel {
    private ResizeListener resizeListener;

    public ResizingSplitLayoutPanel() {
    }

    public ResizingSplitLayoutPanel(int splitterSize) {
        super(splitterSize);
    }

    public void setResizeListener(ResizeListener resizeListener) {
        this.resizeListener = resizeListener;
    }

    public interface ResizeListener {
        void onResize();
    }

    @Override
    public void onResize() {
        super.onResize();
        if ( resizeListener != null) {
            resizeListener.onResize();
        }
    }
}
