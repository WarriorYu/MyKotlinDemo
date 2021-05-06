package com.example.yuxibing.mykotlindemo.javacode.themedemo;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;


import com.example.yuxibing.mykotlindemo.javacode.themedemo.retrofit.Api;
import com.example.yuxibing.mykotlindemo.javacode.themedemo.retrofit.RetrofitService;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 */

/**
 * Author   : yuxibing
 * Create   : 2019/1/16
 * Describe : 皮肤下载服务
 */
public class DownLoadThemeService extends IntentService {


    public DownLoadThemeService() {
        super("DownLoadThemeService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startDowloadService(Context context, String url) {
        Intent intent = new Intent(context, DownLoadThemeService.class);
        intent.putExtra("data", url);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        String url = intent.getStringExtra("data");
        String filePath = FilePathUtil.getSDFilePath();
        if (TextUtils.isEmpty(filePath)) {
            return;
        }
        toDlowload(url, filePath);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void toDlowload(String url, String filePath) {
        RetrofitService downloadService = Api.getInstance();
        Call<ResponseBody> call = downloadService.getSysTheme(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String writtenToDisk = save(response.body().byteStream(), filePath);
                            Log.e("下载完成", writtenToDisk);

                        }
                    }).start();
                } else {
                    Log.e("getSysTheme", "下载失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("getSysTheme", "error");
            }
        });


    }

    /**
     * 将文件写入本地
     *
     * @param destFileName 目标文件名
     * @return 写入完成的文件
     * @throws IOException IO异常
     */
    private File saveFile(InputStream is, String destFileName) throws IOException {
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            File file = new File(destFileName);
            if (file.exists()) {
                file.delete();
            }
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            return file;

        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String save(InputStream is, String filePath) {
        File file = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            if (is != null) {
                File dir = new File(filePath);//sd卡的根目录
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                file = new File(dir, "GuanfuTheme.dl");
                File newFile = new File(dir, "GuanfuTheme.zip");
                if (newFile.exists()) {//如果存在这个压缩文件，先删除
                    newFile.delete();
                }

                // 已读出流作为参数创建一个带有缓冲的输出流
                bis = new BufferedInputStream(is);
                // 创建一个新的写入流，讲读取到的图像数据写入到文件中
                fos = new FileOutputStream(file);
                // 已写入流作为参数创建一个带有缓冲的写入流
                bos = new BufferedOutputStream(fos);

                int read;
                byte[] buffer = new byte[1024];
                while ((read = bis.read(buffer)) != -1) {
                    bos.write(buffer, 0, read);
                }
                bos.flush();
                fos.flush();
                file.renameTo(newFile);//临时dl文件改为zip文件
                String outPutPath = FilePathUtil.getThemeFilePath();
                File outPutFile = new File(outPutPath);//".GuanfuTheme"
                if (outPutFile.exists()) {
                    outPutFile.delete();
                }
                outPutFile.mkdir();
                UnZipFolder(newFile.getAbsolutePath(), outPutPath);//将zip文件解压到".GuanfuTheme"
                if (newFile.exists()) {//解压完删除压缩文件
                    newFile.delete();
                }
//                getThemeJson(outPutFile + "/Info.json");
                return "success";
//                handler.sendMessage(handler.obtainMessage(DownloadManager.STATUS_SUCCESSFUL, newFile.getAbsolutePath()));
            }
        } catch (Exception e) {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (file != null && file.exists()) file.delete();
//            handler.sendEmptyMessage(DownloadManager.STATUS_FAILED);
        }
        return "Failure";
    }

//    private void getThemeJson(String jsonPath) {
//        ThemeResourseModel resourseModel = JsonUtil.parseFileToBean(jsonPath, ThemeResourseModel.class);
//        TTApplication.putThemeResourceModel(TTApplication.context,resourseModel);
//    }

    /**
     * DeCompress the ZIP to the path
     *
     * @param zipFileString name of ZIP
     * @param outPathString path to be unZIP
     * @throws Exception
     */
    public static void UnZipFolder(String zipFileString, String outPathString) throws Exception {
        ZipInputStream inZip = new ZipInputStream(new FileInputStream(zipFileString));
        ZipEntry zipEntry;
        String szName = "";
        while ((zipEntry = inZip.getNextEntry()) != null) {
            szName = zipEntry.getName();
            if (zipEntry.isDirectory()) {
                // get the folder name of the widget
                szName = szName.substring(0, szName.length() - 1);
                File folder = new File(outPathString + File.separator + szName);
                folder.mkdirs();
            } else {
                File file = new File(outPathString + File.separator + szName);
                file.createNewFile();
                // get the output stream of the file
                FileOutputStream out = new FileOutputStream(file);
                int len;
                byte[] buffer = new byte[1024];
                // read (len) bytes into buffer
                while ((len = inZip.read(buffer)) != -1) {
                    // write (len) byte from buffer at the position 0
                    out.write(buffer, 0, len);
                    out.flush();
                }
                out.close();
            }
        }
        inZip.close();
    }

}
