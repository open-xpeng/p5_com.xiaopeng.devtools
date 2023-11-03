package com.xiaopeng.devtools.view.hwcheck;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.lib.utils.c;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ImageDispAdapter.java */
/* loaded from: classes12.dex */
public class a extends RecyclerView.Adapter<C0075a> {
    private ImageView PB;
    private WindowManager PC;
    private Context mContext;
    private List<String> mList = new ArrayList();
    private Bitmap mBitmap = null;
    private WindowManager.LayoutParams PD = new WindowManager.LayoutParams();

    public a(Context context) {
        this.mContext = context;
        this.PC = (WindowManager) this.mContext.getSystemService("window");
        this.PB = new ImageView(this.mContext);
        this.PD.setTitle("HwCheckImageView");
        this.PD.type = 2026;
        this.PD.flags = 201394048;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: b */
    public C0075a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new C0075a(LayoutInflater.from(this.mContext).inflate(R.layout.item_image_disp, (ViewGroup) null));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public void onBindViewHolder(C0075a c0075a, final int i) {
        c0075a.PF.setText(this.mList.get(i));
        c0075a.PF.setTextColor(-1);
        c0075a.PG.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.devtools.view.hwcheck.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a aVar = a.this;
                aVar.dp("/mnt/usbhost/xptestimage/" + ((String) a.this.mList.get(i)));
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    /* compiled from: ImageDispAdapter.java */
    /* renamed from: com.xiaopeng.devtools.view.hwcheck.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static class C0075a extends RecyclerView.ViewHolder {
        private TextView PF;
        private RelativeLayout PG;

        public C0075a(View view) {
            super(view);
            this.PF = (TextView) view.findViewById(R.id.txt_item_image);
            this.PG = (RelativeLayout) view.findViewById(R.id.rl_item_image);
        }
    }

    public void dJ() {
        File[] listFiles;
        File file = new File("/mnt/usbhost/xptestimage/");
        if (file.exists() && file.isDirectory() && file.listFiles() != null) {
            for (File file2 : file.listFiles()) {
                this.mList.add(file2.getName());
                c.f("ImageDispAdapter", "filepath=" + file2.getName());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dp(String str) {
        if (this.mBitmap != null && !this.mBitmap.isRecycled()) {
            this.mBitmap.recycle();
            this.mBitmap = null;
        }
        this.mBitmap = BitmapFactory.decodeFile(str);
        this.PB.setScaleType(ImageView.ScaleType.FIT_XY);
        this.PB.setImageBitmap(this.mBitmap);
        this.PB.setSystemUiVisibility(3846);
        this.PC.addView(this.PB, this.PD);
        this.PB.setOnTouchListener(new View.OnTouchListener() { // from class: com.xiaopeng.devtools.view.hwcheck.a.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                c.f("ImageDispAdapter", "finish view");
                a.this.PB.setSystemUiVisibility(1792);
                a.this.PC.removeView(a.this.PB);
                return true;
            }
        });
    }
}
