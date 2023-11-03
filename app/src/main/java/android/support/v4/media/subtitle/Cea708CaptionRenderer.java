package android.support.v4.media.subtitle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.media.SubtitleData2;
import android.support.v4.media.subtitle.Cea708CCParser;
import android.support.v4.media.subtitle.ClosedCaptionWidget;
import android.support.v4.media.subtitle.SubtitleController;
import android.support.v4.media.subtitle.SubtitleTrack;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.CaptioningManager;
import android.widget.RelativeLayout;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@RequiresApi(28)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes7.dex */
public class Cea708CaptionRenderer extends SubtitleController.Renderer {
    private Cea708CCWidget mCCWidget;
    private final Context mContext;

    public Cea708CaptionRenderer(Context context) {
        this.mContext = context;
    }

    @Override // android.support.v4.media.subtitle.SubtitleController.Renderer
    public boolean supports(MediaFormat mediaFormat) {
        if (mediaFormat.containsKey("mime")) {
            return SubtitleData2.MIMETYPE_TEXT_CEA_708.equals(mediaFormat.getString("mime"));
        }
        return false;
    }

    @Override // android.support.v4.media.subtitle.SubtitleController.Renderer
    public SubtitleTrack createTrack(MediaFormat mediaFormat) {
        if (SubtitleData2.MIMETYPE_TEXT_CEA_708.equals(mediaFormat.getString("mime"))) {
            if (this.mCCWidget == null) {
                this.mCCWidget = new Cea708CCWidget(this, this.mContext);
            }
            return new Cea708CaptionTrack(this.mCCWidget, mediaFormat);
        }
        throw new RuntimeException("No matching format: " + mediaFormat.toString());
    }

    /* loaded from: classes7.dex */
    static class Cea708CaptionTrack extends SubtitleTrack {
        private final Cea708CCParser mCCParser;
        private final Cea708CCWidget mRenderingWidget;

        Cea708CaptionTrack(Cea708CCWidget cea708CCWidget, MediaFormat mediaFormat) {
            super(mediaFormat);
            this.mRenderingWidget = cea708CCWidget;
            this.mCCParser = new Cea708CCParser(this.mRenderingWidget);
        }

        @Override // android.support.v4.media.subtitle.SubtitleTrack
        public void onData(byte[] bArr, boolean z, long j) {
            this.mCCParser.parse(bArr);
        }

        @Override // android.support.v4.media.subtitle.SubtitleTrack
        public SubtitleTrack.RenderingWidget getRenderingWidget() {
            return this.mRenderingWidget;
        }

        @Override // android.support.v4.media.subtitle.SubtitleTrack
        public void updateView(ArrayList<SubtitleTrack.Cue> arrayList) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class Cea708CCWidget extends ClosedCaptionWidget implements Cea708CCParser.DisplayListener {
        private final CCHandler mCCHandler;

        Cea708CCWidget(Cea708CaptionRenderer cea708CaptionRenderer, Context context) {
            this(cea708CaptionRenderer, context, null);
        }

        Cea708CCWidget(Cea708CaptionRenderer cea708CaptionRenderer, Context context, AttributeSet attributeSet) {
            this(cea708CaptionRenderer, context, attributeSet, 0);
        }

        Cea708CCWidget(Cea708CaptionRenderer cea708CaptionRenderer, Context context, AttributeSet attributeSet, int i) {
            this(context, attributeSet, i, 0);
        }

        Cea708CCWidget(Context context, AttributeSet attributeSet, int i, int i2) {
            super(context, attributeSet, i, i2);
            this.mCCHandler = new CCHandler((CCLayout) this.mClosedCaptionLayout);
        }

        @Override // android.support.v4.media.subtitle.ClosedCaptionWidget
        public ClosedCaptionWidget.ClosedCaptionLayout createCaptionLayout(Context context) {
            return new CCLayout(context);
        }

        @Override // android.support.v4.media.subtitle.Cea708CCParser.DisplayListener
        public void emitEvent(Cea708CCParser.CaptionEvent captionEvent) {
            this.mCCHandler.processCaptionEvent(captionEvent);
            setSize(getWidth(), getHeight());
            if (this.mListener != null) {
                this.mListener.onChanged(this);
            }
        }

        @Override // android.view.View
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            ((ViewGroup) this.mClosedCaptionLayout).draw(canvas);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes7.dex */
        public class ScaledLayout extends ViewGroup {
            private static final boolean DEBUG = false;
            private static final String TAG = "ScaledLayout";
            private Rect[] mRectArray;
            private final Comparator<Rect> mRectTopLeftSorter;

            ScaledLayout(Context context) {
                super(context);
                this.mRectTopLeftSorter = new Comparator<Rect>() { // from class: android.support.v4.media.subtitle.Cea708CaptionRenderer.Cea708CCWidget.ScaledLayout.1
                    @Override // java.util.Comparator
                    public int compare(Rect rect, Rect rect2) {
                        if (rect.top != rect2.top) {
                            return rect.top - rect2.top;
                        }
                        return rect.left - rect2.left;
                    }
                };
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* loaded from: classes7.dex */
            public class ScaledLayoutParams extends ViewGroup.LayoutParams {
                public static final float SCALE_UNSPECIFIED = -1.0f;
                public float scaleEndCol;
                public float scaleEndRow;
                public float scaleStartCol;
                public float scaleStartRow;

                ScaledLayoutParams(float f, float f2, float f3, float f4) {
                    super(-1, -1);
                    this.scaleStartRow = f;
                    this.scaleEndRow = f2;
                    this.scaleStartCol = f3;
                    this.scaleEndCol = f4;
                }

                ScaledLayoutParams(Context context, AttributeSet attributeSet) {
                    super(-1, -1);
                }
            }

            @Override // android.view.ViewGroup
            public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
                return new ScaledLayoutParams(getContext(), attributeSet);
            }

            @Override // android.view.ViewGroup
            protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
                return layoutParams instanceof ScaledLayoutParams;
            }

            @Override // android.view.View
            protected void onMeasure(int i, int i2) {
                int i3;
                int i4;
                int i5;
                int size = View.MeasureSpec.getSize(i);
                int size2 = View.MeasureSpec.getSize(i2);
                int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
                int paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
                int childCount = getChildCount();
                this.mRectArray = new Rect[childCount];
                int i6 = 0;
                while (i6 < childCount) {
                    View childAt = getChildAt(i6);
                    ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                    if (!(layoutParams instanceof ScaledLayoutParams)) {
                        throw new RuntimeException("A child of ScaledLayout cannot have the UNSPECIFIED scale factors");
                    }
                    ScaledLayoutParams scaledLayoutParams = (ScaledLayoutParams) layoutParams;
                    float f = scaledLayoutParams.scaleStartRow;
                    float f2 = scaledLayoutParams.scaleEndRow;
                    float f3 = scaledLayoutParams.scaleStartCol;
                    float f4 = scaledLayoutParams.scaleEndCol;
                    if (f < 0.0f || f > 1.0f) {
                        throw new RuntimeException("A child of ScaledLayout should have a range of scaleStartRow between 0 and 1");
                    }
                    if (f2 < f || i4 > 0) {
                        throw new RuntimeException("A child of ScaledLayout should have a range of scaleEndRow between scaleStartRow and 1");
                    }
                    if (f4 < 0.0f || f4 > 1.0f) {
                        throw new RuntimeException("A child of ScaledLayout should have a range of scaleStartCol between 0 and 1");
                    }
                    if (f4 < f3 || i5 > 0) {
                        throw new RuntimeException("A child of ScaledLayout should have a range of scaleEndCol between scaleStartCol and 1");
                    }
                    float f5 = paddingLeft;
                    int i7 = paddingLeft;
                    float f6 = paddingTop;
                    int i8 = size;
                    int i9 = size2;
                    int i10 = childCount;
                    this.mRectArray[i6] = new Rect((int) (f3 * f5), (int) (f * f6), (int) (f4 * f5), (int) (f2 * f6));
                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (f5 * (f4 - f3)), 1073741824);
                    childAt.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, 0));
                    if (childAt.getMeasuredHeight() > this.mRectArray[i6].height()) {
                        int measuredHeight = ((childAt.getMeasuredHeight() - this.mRectArray[i6].height()) + 1) / 2;
                        this.mRectArray[i6].bottom += measuredHeight;
                        this.mRectArray[i6].top -= measuredHeight;
                        if (this.mRectArray[i6].top < 0) {
                            this.mRectArray[i6].bottom -= this.mRectArray[i6].top;
                            this.mRectArray[i6].top = 0;
                        }
                        if (this.mRectArray[i6].bottom > paddingTop) {
                            this.mRectArray[i6].top -= this.mRectArray[i6].bottom - paddingTop;
                            this.mRectArray[i6].bottom = paddingTop;
                        }
                    }
                    childAt.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec((int) (f6 * (f2 - f)), 1073741824));
                    i6++;
                    paddingLeft = i7;
                    size = i8;
                    size2 = i9;
                    childCount = i10;
                }
                int i11 = size;
                int i12 = size2;
                int i13 = childCount;
                int[] iArr = new int[i13];
                Rect[] rectArr = new Rect[i13];
                int i14 = 0;
                for (int i15 = 0; i15 < i13; i15++) {
                    if (getChildAt(i15).getVisibility() == 0) {
                        iArr[i14] = i14;
                        rectArr[i14] = this.mRectArray[i15];
                        i14++;
                    }
                }
                Arrays.sort(rectArr, 0, i14, this.mRectTopLeftSorter);
                int i16 = 0;
                while (true) {
                    i3 = i14 - 1;
                    if (i16 >= i3) {
                        break;
                    }
                    int i17 = i16 + 1;
                    for (int i18 = i17; i18 < i14; i18++) {
                        if (Rect.intersects(rectArr[i16], rectArr[i18])) {
                            iArr[i18] = iArr[i16];
                            rectArr[i18].set(rectArr[i18].left, rectArr[i16].bottom, rectArr[i18].right, rectArr[i16].bottom + rectArr[i18].height());
                        }
                    }
                    i16 = i17;
                }
                while (i3 >= 0) {
                    if (rectArr[i3].bottom > paddingTop) {
                        int i19 = rectArr[i3].bottom - paddingTop;
                        for (int i20 = 0; i20 <= i3; i20++) {
                            if (iArr[i3] == iArr[i20]) {
                                rectArr[i20].set(rectArr[i20].left, rectArr[i20].top - i19, rectArr[i20].right, rectArr[i20].bottom - i19);
                            }
                        }
                    }
                    i3--;
                }
                setMeasuredDimension(i11, i12);
            }

            @Override // android.view.ViewGroup, android.view.View
            protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                int childCount = getChildCount();
                for (int i5 = 0; i5 < childCount; i5++) {
                    View childAt = getChildAt(i5);
                    if (childAt.getVisibility() != 8) {
                        childAt.layout(this.mRectArray[i5].left + paddingLeft, this.mRectArray[i5].top + paddingTop, this.mRectArray[i5].right + paddingTop, this.mRectArray[i5].bottom + paddingLeft);
                    }
                }
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas canvas) {
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = getChildAt(i);
                    if (childAt.getVisibility() != 8) {
                        if (i < this.mRectArray.length) {
                            int save = canvas.save();
                            canvas.translate(this.mRectArray[i].left + paddingLeft, this.mRectArray[i].top + paddingTop);
                            childAt.draw(canvas);
                            canvas.restoreToCount(save);
                        } else {
                            return;
                        }
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes7.dex */
        public class CCLayout extends ScaledLayout implements ClosedCaptionWidget.ClosedCaptionLayout {
            private static final float SAFE_TITLE_AREA_SCALE_END_X = 0.9f;
            private static final float SAFE_TITLE_AREA_SCALE_END_Y = 0.9f;
            private static final float SAFE_TITLE_AREA_SCALE_START_X = 0.1f;
            private static final float SAFE_TITLE_AREA_SCALE_START_Y = 0.1f;
            private final ScaledLayout mSafeTitleAreaLayout;

            CCLayout(Context context) {
                super(context);
                this.mSafeTitleAreaLayout = new ScaledLayout(context);
                addView(this.mSafeTitleAreaLayout, new ScaledLayout.ScaledLayoutParams(0.1f, 0.9f, 0.1f, 0.9f));
            }

            public void addOrUpdateViewToSafeTitleArea(CCWindowLayout cCWindowLayout, ScaledLayout.ScaledLayoutParams scaledLayoutParams) {
                if (this.mSafeTitleAreaLayout.indexOfChild(cCWindowLayout) < 0) {
                    this.mSafeTitleAreaLayout.addView(cCWindowLayout, scaledLayoutParams);
                } else {
                    this.mSafeTitleAreaLayout.updateViewLayout(cCWindowLayout, scaledLayoutParams);
                }
            }

            public void removeViewFromSafeTitleArea(CCWindowLayout cCWindowLayout) {
                this.mSafeTitleAreaLayout.removeView(cCWindowLayout);
            }

            @Override // android.support.v4.media.subtitle.ClosedCaptionWidget.ClosedCaptionLayout
            public void setCaptionStyle(CaptioningManager.CaptionStyle captionStyle) {
                int childCount = this.mSafeTitleAreaLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    ((CCWindowLayout) this.mSafeTitleAreaLayout.getChildAt(i)).setCaptionStyle(captionStyle);
                }
            }

            @Override // android.support.v4.media.subtitle.ClosedCaptionWidget.ClosedCaptionLayout
            public void setFontScale(float f) {
                int childCount = this.mSafeTitleAreaLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    ((CCWindowLayout) this.mSafeTitleAreaLayout.getChildAt(i)).setFontScale(f);
                }
            }
        }

        /* loaded from: classes7.dex */
        class CCHandler implements Handler.Callback {
            private static final int CAPTION_ALL_WINDOWS_BITMAP = 255;
            private static final long CAPTION_CLEAR_INTERVAL_MS = 60000;
            private static final int CAPTION_WINDOWS_MAX = 8;
            private static final boolean DEBUG = false;
            private static final int MSG_CAPTION_CLEAR = 2;
            private static final int MSG_DELAY_CANCEL = 1;
            private static final String TAG = "CCHandler";
            private static final int TENTHS_OF_SECOND_IN_MILLIS = 100;
            private final CCLayout mCCLayout;
            private CCWindowLayout mCurrentWindowLayout;
            private boolean mIsDelayed = false;
            private final CCWindowLayout[] mCaptionWindowLayouts = new CCWindowLayout[8];
            private final ArrayList<Cea708CCParser.CaptionEvent> mPendingCaptionEvents = new ArrayList<>();
            private final Handler mHandler = new Handler(this);

            CCHandler(CCLayout cCLayout) {
                this.mCCLayout = cCLayout;
            }

            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        delayCancel();
                        return true;
                    case 2:
                        clearWindows(255);
                        return true;
                    default:
                        return false;
                }
            }

            public void processCaptionEvent(Cea708CCParser.CaptionEvent captionEvent) {
                if (this.mIsDelayed) {
                    this.mPendingCaptionEvents.add(captionEvent);
                    return;
                }
                switch (captionEvent.type) {
                    case 1:
                        sendBufferToCurrentWindow((String) captionEvent.obj);
                        return;
                    case 2:
                        sendControlToCurrentWindow(((Character) captionEvent.obj).charValue());
                        return;
                    case 3:
                        setCurrentWindowLayout(((Integer) captionEvent.obj).intValue());
                        return;
                    case 4:
                        clearWindows(((Integer) captionEvent.obj).intValue());
                        return;
                    case 5:
                        displayWindows(((Integer) captionEvent.obj).intValue());
                        return;
                    case 6:
                        hideWindows(((Integer) captionEvent.obj).intValue());
                        return;
                    case 7:
                        toggleWindows(((Integer) captionEvent.obj).intValue());
                        return;
                    case 8:
                        deleteWindows(((Integer) captionEvent.obj).intValue());
                        return;
                    case 9:
                        delay(((Integer) captionEvent.obj).intValue());
                        return;
                    case 10:
                        delayCancel();
                        return;
                    case 11:
                        reset();
                        return;
                    case 12:
                        setPenAttr((Cea708CCParser.CaptionPenAttr) captionEvent.obj);
                        return;
                    case 13:
                        setPenColor((Cea708CCParser.CaptionPenColor) captionEvent.obj);
                        return;
                    case 14:
                        setPenLocation((Cea708CCParser.CaptionPenLocation) captionEvent.obj);
                        return;
                    case 15:
                        setWindowAttr((Cea708CCParser.CaptionWindowAttr) captionEvent.obj);
                        return;
                    case 16:
                        defineWindow((Cea708CCParser.CaptionWindow) captionEvent.obj);
                        return;
                    default:
                        return;
                }
            }

            private void setCurrentWindowLayout(int i) {
                CCWindowLayout cCWindowLayout;
                if (i < 0 || i >= this.mCaptionWindowLayouts.length || (cCWindowLayout = this.mCaptionWindowLayouts[i]) == null) {
                    return;
                }
                this.mCurrentWindowLayout = cCWindowLayout;
            }

            private ArrayList<CCWindowLayout> getWindowsFromBitmap(int i) {
                CCWindowLayout cCWindowLayout;
                ArrayList<CCWindowLayout> arrayList = new ArrayList<>();
                for (int i2 = 0; i2 < 8; i2++) {
                    if (((1 << i2) & i) != 0 && (cCWindowLayout = this.mCaptionWindowLayouts[i2]) != null) {
                        arrayList.add(cCWindowLayout);
                    }
                }
                return arrayList;
            }

            private void clearWindows(int i) {
                if (i == 0) {
                    return;
                }
                Iterator<CCWindowLayout> it = getWindowsFromBitmap(i).iterator();
                while (it.hasNext()) {
                    it.next().clear();
                }
            }

            private void displayWindows(int i) {
                if (i == 0) {
                    return;
                }
                Iterator<CCWindowLayout> it = getWindowsFromBitmap(i).iterator();
                while (it.hasNext()) {
                    it.next().show();
                }
            }

            private void hideWindows(int i) {
                if (i == 0) {
                    return;
                }
                Iterator<CCWindowLayout> it = getWindowsFromBitmap(i).iterator();
                while (it.hasNext()) {
                    it.next().hide();
                }
            }

            private void toggleWindows(int i) {
                if (i == 0) {
                    return;
                }
                Iterator<CCWindowLayout> it = getWindowsFromBitmap(i).iterator();
                while (it.hasNext()) {
                    CCWindowLayout next = it.next();
                    if (next.isShown()) {
                        next.hide();
                    } else {
                        next.show();
                    }
                }
            }

            private void deleteWindows(int i) {
                if (i == 0) {
                    return;
                }
                Iterator<CCWindowLayout> it = getWindowsFromBitmap(i).iterator();
                while (it.hasNext()) {
                    CCWindowLayout next = it.next();
                    next.removeFromCaptionView();
                    this.mCaptionWindowLayouts[next.getCaptionWindowId()] = null;
                }
            }

            public void reset() {
                this.mCurrentWindowLayout = null;
                this.mIsDelayed = false;
                this.mPendingCaptionEvents.clear();
                for (int i = 0; i < 8; i++) {
                    if (this.mCaptionWindowLayouts[i] != null) {
                        this.mCaptionWindowLayouts[i].removeFromCaptionView();
                    }
                    this.mCaptionWindowLayouts[i] = null;
                }
                this.mCCLayout.setVisibility(4);
                this.mHandler.removeMessages(2);
            }

            private void setWindowAttr(Cea708CCParser.CaptionWindowAttr captionWindowAttr) {
                if (this.mCurrentWindowLayout != null) {
                    this.mCurrentWindowLayout.setWindowAttr(captionWindowAttr);
                }
            }

            private void defineWindow(Cea708CCParser.CaptionWindow captionWindow) {
                int i;
                if (captionWindow == null || (i = captionWindow.id) < 0 || i >= this.mCaptionWindowLayouts.length) {
                    return;
                }
                CCWindowLayout cCWindowLayout = this.mCaptionWindowLayouts[i];
                if (cCWindowLayout == null) {
                    cCWindowLayout = new CCWindowLayout(Cea708CCWidget.this, this.mCCLayout.getContext());
                }
                cCWindowLayout.initWindow(this.mCCLayout, captionWindow);
                this.mCaptionWindowLayouts[i] = cCWindowLayout;
                this.mCurrentWindowLayout = cCWindowLayout;
            }

            private void delay(int i) {
                if (i < 0 || i > 255) {
                    return;
                }
                this.mIsDelayed = true;
                this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1), i * 100);
            }

            private void delayCancel() {
                this.mIsDelayed = false;
                processPendingBuffer();
            }

            private void processPendingBuffer() {
                Iterator<Cea708CCParser.CaptionEvent> it = this.mPendingCaptionEvents.iterator();
                while (it.hasNext()) {
                    processCaptionEvent(it.next());
                }
                this.mPendingCaptionEvents.clear();
            }

            private void sendControlToCurrentWindow(char c) {
                if (this.mCurrentWindowLayout != null) {
                    this.mCurrentWindowLayout.sendControl(c);
                }
            }

            private void sendBufferToCurrentWindow(String str) {
                if (this.mCurrentWindowLayout != null) {
                    this.mCurrentWindowLayout.sendBuffer(str);
                    this.mHandler.removeMessages(2);
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(2), CAPTION_CLEAR_INTERVAL_MS);
                }
            }

            private void setPenAttr(Cea708CCParser.CaptionPenAttr captionPenAttr) {
                if (this.mCurrentWindowLayout != null) {
                    this.mCurrentWindowLayout.setPenAttr(captionPenAttr);
                }
            }

            private void setPenColor(Cea708CCParser.CaptionPenColor captionPenColor) {
                if (this.mCurrentWindowLayout != null) {
                    this.mCurrentWindowLayout.setPenColor(captionPenColor);
                }
            }

            private void setPenLocation(Cea708CCParser.CaptionPenLocation captionPenLocation) {
                if (this.mCurrentWindowLayout != null) {
                    this.mCurrentWindowLayout.setPenLocation(captionPenLocation.row, captionPenLocation.column);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes7.dex */
        public class CCWindowLayout extends RelativeLayout implements View.OnLayoutChangeListener {
            private static final int ANCHOR_HORIZONTAL_16_9_MAX = 209;
            private static final int ANCHOR_HORIZONTAL_MODE_CENTER = 1;
            private static final int ANCHOR_HORIZONTAL_MODE_LEFT = 0;
            private static final int ANCHOR_HORIZONTAL_MODE_RIGHT = 2;
            private static final int ANCHOR_MODE_DIVIDER = 3;
            private static final int ANCHOR_RELATIVE_POSITIONING_MAX = 99;
            private static final int ANCHOR_VERTICAL_MAX = 74;
            private static final int ANCHOR_VERTICAL_MODE_BOTTOM = 2;
            private static final int ANCHOR_VERTICAL_MODE_CENTER = 1;
            private static final int ANCHOR_VERTICAL_MODE_TOP = 0;
            private static final int MAX_COLUMN_COUNT_16_9 = 42;
            private static final float PROPORTION_PEN_SIZE_LARGE = 1.25f;
            private static final float PROPORTION_PEN_SIZE_SMALL = 0.75f;
            private static final String TAG = "CCWindowLayout";
            private final SpannableStringBuilder mBuilder;
            private CCLayout mCCLayout;
            private CCView mCCView;
            private CaptioningManager.CaptionStyle mCaptionStyle;
            private int mCaptionWindowId;
            private final List<CharacterStyle> mCharacterStyles;
            private float mFontScale;
            private int mLastCaptionLayoutHeight;
            private int mLastCaptionLayoutWidth;
            private int mRow;
            private int mRowLimit;
            private float mTextSize;
            private String mWidestChar;

            CCWindowLayout(Cea708CCWidget cea708CCWidget, Context context) {
                this(cea708CCWidget, context, null);
            }

            CCWindowLayout(Cea708CCWidget cea708CCWidget, Context context, AttributeSet attributeSet) {
                this(cea708CCWidget, context, attributeSet, 0);
            }

            CCWindowLayout(Cea708CCWidget cea708CCWidget, Context context, AttributeSet attributeSet, int i) {
                this(context, attributeSet, i, 0);
            }

            CCWindowLayout(Context context, AttributeSet attributeSet, int i, int i2) {
                super(context, attributeSet, i, i2);
                this.mRowLimit = 0;
                this.mBuilder = new SpannableStringBuilder();
                this.mCharacterStyles = new ArrayList();
                this.mRow = -1;
                this.mCCView = new CCView(Cea708CCWidget.this, context);
                addView(this.mCCView, new RelativeLayout.LayoutParams(-2, -2));
                CaptioningManager captioningManager = (CaptioningManager) context.getSystemService("captioning");
                this.mFontScale = captioningManager.getFontScale();
                setCaptionStyle(captioningManager.getUserStyle());
                this.mCCView.setText("");
                updateWidestChar();
            }

            public void setCaptionStyle(CaptioningManager.CaptionStyle captionStyle) {
                this.mCaptionStyle = captionStyle;
                this.mCCView.setCaptionStyle(captionStyle);
            }

            public void setFontScale(float f) {
                this.mFontScale = f;
                updateTextSize();
            }

            public int getCaptionWindowId() {
                return this.mCaptionWindowId;
            }

            public void setCaptionWindowId(int i) {
                this.mCaptionWindowId = i;
            }

            public void clear() {
                clearText();
                hide();
            }

            public void show() {
                setVisibility(0);
                requestLayout();
            }

            public void hide() {
                setVisibility(4);
                requestLayout();
            }

            public void setPenAttr(Cea708CCParser.CaptionPenAttr captionPenAttr) {
                this.mCharacterStyles.clear();
                if (captionPenAttr.italic) {
                    this.mCharacterStyles.add(new StyleSpan(2));
                }
                if (captionPenAttr.underline) {
                    this.mCharacterStyles.add(new UnderlineSpan());
                }
                int i = captionPenAttr.penSize;
                if (i == 0) {
                    this.mCharacterStyles.add(new RelativeSizeSpan(0.75f));
                } else if (i == 2) {
                    this.mCharacterStyles.add(new RelativeSizeSpan((float) PROPORTION_PEN_SIZE_LARGE));
                }
                int i2 = captionPenAttr.penOffset;
                if (i2 == 0) {
                    this.mCharacterStyles.add(new SubscriptSpan());
                } else if (i2 == 2) {
                    this.mCharacterStyles.add(new SuperscriptSpan());
                }
            }

            public void setPenColor(Cea708CCParser.CaptionPenColor captionPenColor) {
            }

            public void setPenLocation(int i, int i2) {
                if (this.mRow >= 0) {
                    for (int i3 = this.mRow; i3 < i; i3++) {
                        appendText("\n");
                    }
                }
                this.mRow = i;
            }

            public void setWindowAttr(Cea708CCParser.CaptionWindowAttr captionWindowAttr) {
            }

            public void sendBuffer(String str) {
                appendText(str);
            }

            public void sendControl(char c) {
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            public void initWindow(CCLayout cCLayout, Cea708CCParser.CaptionWindow captionWindow) {
                float f;
                float f2;
                float f3;
                float f4;
                if (this.mCCLayout != cCLayout) {
                    if (this.mCCLayout != null) {
                        this.mCCLayout.removeOnLayoutChangeListener(this);
                    }
                    this.mCCLayout = cCLayout;
                    this.mCCLayout.addOnLayoutChangeListener(this);
                    updateWidestChar();
                }
                float f5 = captionWindow.anchorVertical / (captionWindow.relativePositioning ? 99 : 74);
                float f6 = captionWindow.anchorHorizontal / (captionWindow.relativePositioning ? 99 : ANCHOR_HORIZONTAL_16_9_MAX);
                float f7 = 0.0f;
                float f8 = 1.0f;
                if (f5 < 0.0f || f5 > 1.0f) {
                    Log.i(TAG, "The vertical position of the anchor point should be at the range of 0 and 1 but " + f5);
                    f5 = Math.max(0.0f, Math.min(f5, 1.0f));
                }
                if (f6 < 0.0f || f6 > 1.0f) {
                    Log.i(TAG, "The horizontal position of the anchor point should be at the range of 0 and 1 but " + f6);
                    f6 = Math.max(0.0f, Math.min(f6, 1.0f));
                }
                int i = 17;
                int i2 = captionWindow.anchorId % 3;
                int i3 = captionWindow.anchorId / 3;
                switch (i2) {
                    case 0:
                        this.mCCView.setAlignment(Layout.Alignment.ALIGN_NORMAL);
                        f = f6;
                        f2 = 1.0f;
                        i = 3;
                        break;
                    case 1:
                        float min = Math.min(1.0f - f6, f6);
                        int min2 = Math.min(getScreenColumnCount(), captionWindow.columnCount + 1);
                        StringBuilder sb = new StringBuilder();
                        for (int i4 = 0; i4 < min2; i4++) {
                            sb.append(this.mWidestChar);
                        }
                        Paint paint = new Paint();
                        paint.setTypeface(this.mCaptionStyle.getTypeface());
                        paint.setTextSize(this.mTextSize);
                        float measureText = this.mCCLayout.getWidth() > 0 ? (paint.measureText(sb.toString()) / 2.0f) / (this.mCCLayout.getWidth() * 0.8f) : 0.0f;
                        if (measureText <= 0.0f || measureText >= f6) {
                            this.mCCView.setAlignment(Layout.Alignment.ALIGN_CENTER);
                            f2 = f6 + min;
                            f = f6 - min;
                            i = 1;
                            break;
                        } else {
                            this.mCCView.setAlignment(Layout.Alignment.ALIGN_NORMAL);
                            f6 -= measureText;
                            f = f6;
                            f2 = 1.0f;
                            i = 3;
                            break;
                        }
                        break;
                    case 2:
                        i = 5;
                        f2 = f6;
                        f = 0.0f;
                        break;
                    default:
                        f = 0.0f;
                        f2 = 1.0f;
                        break;
                }
                switch (i3) {
                    case 0:
                        i |= 48;
                        f3 = f5;
                        f4 = f8;
                        break;
                    case 1:
                        i |= 16;
                        float min3 = Math.min(1.0f - f5, f5);
                        f7 = f5 - min3;
                        f8 = f5 + min3;
                        f3 = f7;
                        f4 = f8;
                        break;
                    case 2:
                        i |= 80;
                        f4 = f5;
                        f3 = 0.0f;
                        break;
                    default:
                        f3 = f7;
                        f4 = f8;
                        break;
                }
                CCLayout cCLayout2 = this.mCCLayout;
                CCLayout cCLayout3 = this.mCCLayout;
                cCLayout3.getClass();
                cCLayout2.addOrUpdateViewToSafeTitleArea(this, new ScaledLayout.ScaledLayoutParams(f3, f4, f, f2));
                setCaptionWindowId(captionWindow.id);
                setRowLimit(captionWindow.rowCount);
                setGravity(i);
                if (captionWindow.visible) {
                    show();
                } else {
                    hide();
                }
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                int i9 = i3 - i;
                int i10 = i4 - i2;
                if (i9 != this.mLastCaptionLayoutWidth || i10 != this.mLastCaptionLayoutHeight) {
                    this.mLastCaptionLayoutWidth = i9;
                    this.mLastCaptionLayoutHeight = i10;
                    updateTextSize();
                }
            }

            private void updateWidestChar() {
                Paint paint = new Paint();
                paint.setTypeface(this.mCaptionStyle.getTypeface());
                Charset forName = Charset.forName("ISO-8859-1");
                float f = 0.0f;
                for (int i = 0; i < 256; i++) {
                    String str = new String(new byte[]{(byte) i}, forName);
                    float measureText = paint.measureText(str);
                    if (f < measureText) {
                        this.mWidestChar = str;
                        f = measureText;
                    }
                }
                updateTextSize();
            }

            private void updateTextSize() {
                if (this.mCCLayout == null) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                int screenColumnCount = getScreenColumnCount();
                for (int i = 0; i < screenColumnCount; i++) {
                    sb.append(this.mWidestChar);
                }
                String sb2 = sb.toString();
                Paint paint = new Paint();
                paint.setTypeface(this.mCaptionStyle.getTypeface());
                float f = 0.0f;
                float f2 = 255.0f;
                while (f < f2) {
                    float f3 = (f + f2) / 2.0f;
                    paint.setTextSize(f3);
                    if (this.mCCLayout.getWidth() * 0.8f > paint.measureText(sb2)) {
                        f = f3 + 0.01f;
                    } else {
                        f2 = f3 - 0.01f;
                    }
                }
                this.mTextSize = f2 * this.mFontScale;
                this.mCCView.setTextSize(this.mTextSize);
            }

            private int getScreenColumnCount() {
                return 42;
            }

            public void removeFromCaptionView() {
                if (this.mCCLayout != null) {
                    this.mCCLayout.removeViewFromSafeTitleArea(this);
                    this.mCCLayout.removeOnLayoutChangeListener(this);
                    this.mCCLayout = null;
                }
            }

            public void setText(String str) {
                updateText(str, false);
            }

            public void appendText(String str) {
                updateText(str, true);
            }

            public void clearText() {
                this.mBuilder.clear();
                this.mCCView.setText("");
            }

            private void updateText(String str, boolean z) {
                if (!z) {
                    this.mBuilder.clear();
                }
                if (str != null && str.length() > 0) {
                    int length = this.mBuilder.length();
                    this.mBuilder.append((CharSequence) str);
                    for (CharacterStyle characterStyle : this.mCharacterStyles) {
                        this.mBuilder.setSpan(characterStyle, length, this.mBuilder.length(), 33);
                    }
                }
                String[] split = TextUtils.split(this.mBuilder.toString(), "\n");
                this.mBuilder.delete(0, this.mBuilder.length() - TextUtils.join("\n", Arrays.copyOfRange(split, Math.max(0, split.length - (this.mRowLimit + 1)), split.length)).length());
                int length2 = this.mBuilder.length() - 1;
                int i = 0;
                while (i <= length2 && this.mBuilder.charAt(i) <= ' ') {
                    i++;
                }
                int i2 = length2;
                while (i2 >= i && this.mBuilder.charAt(i2) <= ' ') {
                    i2--;
                }
                if (i == 0 && i2 == length2) {
                    this.mCCView.setText(this.mBuilder);
                    return;
                }
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                spannableStringBuilder.append((CharSequence) this.mBuilder);
                if (i2 < length2) {
                    spannableStringBuilder.delete(i2 + 1, length2 + 1);
                }
                if (i > 0) {
                    spannableStringBuilder.delete(0, i);
                }
                this.mCCView.setText(spannableStringBuilder);
            }

            public void setRowLimit(int i) {
                if (i < 0) {
                    throw new IllegalArgumentException("A rowLimit should have a positive number");
                }
                this.mRowLimit = i;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes7.dex */
        public class CCView extends SubtitleView {
            CCView(Cea708CCWidget cea708CCWidget, Context context) {
                this(cea708CCWidget, context, null);
            }

            CCView(Cea708CCWidget cea708CCWidget, Context context, AttributeSet attributeSet) {
                this(cea708CCWidget, context, attributeSet, 0);
            }

            CCView(Cea708CCWidget cea708CCWidget, Context context, AttributeSet attributeSet, int i) {
                this(context, attributeSet, i, 0);
            }

            CCView(Context context, AttributeSet attributeSet, int i, int i2) {
                super(context, attributeSet, i, i2);
            }

            void setCaptionStyle(CaptioningManager.CaptionStyle captionStyle) {
                if (captionStyle.hasForegroundColor()) {
                    setForegroundColor(captionStyle.foregroundColor);
                }
                if (captionStyle.hasBackgroundColor()) {
                    setBackgroundColor(captionStyle.backgroundColor);
                }
                if (captionStyle.hasEdgeType()) {
                    setEdgeType(captionStyle.edgeType);
                }
                if (captionStyle.hasEdgeColor()) {
                    setEdgeColor(captionStyle.edgeColor);
                }
                setTypeface(captionStyle.getTypeface());
            }
        }
    }
}
