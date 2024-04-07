package org.stein;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;

/**
 *
 * 音频或视频获取上传凭证的示例
 */
public class AudioOrVideoCreateUploadDemo {

    /**
     * 读取AccessKey信息
     */
    public static DefaultAcsClient initVodClient() {
        // 点播服务接入地域
        String regionId = "cn-shanghai";
        // 阿里云账号AccessKey拥有所有API的访问权限，建议您使用RAM用户进行API访问或日常运维。
        // 强烈建议不要把AccessKey ID和AccessKey Secret保存到工程代码里，否则可能导致AccessKey泄露，威胁您账号下所有资源的安全。
        // 本示例通过从环境变量中读取AccessKey，来实现API访问的身份验证。运行代码示例前，请配置环境变量ALIBABA_CLOUD_ACCESS_KEY_ID和ALIBABA_CLOUD_ACCESS_KEY_SECRET。
        DefaultProfile profile = DefaultProfile.getProfile(regionId, "LTAI5t6svFCu4P6xbYDW8chP", "Bu7djODxYnwGrcok0d99fojgAGju2Q");
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    /**
     * 获取音/视频上传地址和凭证
     * @param client 发送请求客户端
     * @return CreateUploadVideoResponse 获取音/视频上传地址和凭证响应数据
     * @throws Exception
     */
    public static CreateUploadVideoResponse createUploadVideo(DefaultAcsClient client) throws Exception {
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setTitle("this is a sample");
        request.setFileName("D:\\BaiduNetdiskDownload\\6 - What If I Want to Move Faster.mp4");

        //UserData，用户自定义设置参数，用户需要单独回调URL及数据透传时设置(非必须)
        //JSONObject userData = new JSONObject();

        //UserData回调部分设置
        //消息回调设置，指定时以此为准，否则以全局设置的事件通知为准
        //JSONObject messageCallback = new JSONObject();
        //设置回调地址
        //messageCallback.put("CallbackURL", "http://192.168.0.1/16");
        //设置回调方式，默认为http
        //messageCallback.put("CallbackType", "http");
        //userData.put("MessageCallback", messageCallback.toJSONString());

        //UserData透传数据部分设置
        //用户自定义的扩展字段，用于回调时透传返回
        //JSONObject extend = new JSONObject();
        //extend.put("MyId", "user-defined-id");
        //userData.put("Extend", extend.toJSONString());

        //request.setUserData(userData.toJSONString());

        return client.getAcsResponse(request);
    }

    /**
     * 请求示例
     */
    public static void main(String[] argv) {
        try {
            DefaultAcsClient client = initVodClient();
            CreateUploadVideoResponse response = new CreateUploadVideoResponse();
            response = createUploadVideo(client);
            System.out.print("VideoId = " + response.getVideoId() + "\n");
            System.out.print("UploadAddress = " + response.getUploadAddress() + "\n");
            System.out.print("UploadAuth = " + response.getUploadAuth() + "\n");
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
    }
}