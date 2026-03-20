package net.hwyz.iov.cloud.tsp.account.api.feign.mp;

import net.hwyz.iov.cloud.framework.common.bean.ClientAccount;
import net.hwyz.iov.cloud.framework.common.bean.Response;
import net.hwyz.iov.cloud.tsp.account.api.contract.AccountMp;
import net.hwyz.iov.cloud.tsp.account.api.contract.AccountQrcodeMp;
import net.hwyz.iov.cloud.tsp.oss.api.contract.PreSignedUrl;

import java.util.Map;

/**
 * 账户相关手机接口
 *
 * @author hwyz_leo
 */
public interface AccountMpApi {

    /**
     * 获取账号信息
     *
     * @param clientAccount 终端用户
     * @return 手机账户信息
     */
    Response<AccountMp> getAccountInfo(ClientAccount clientAccount);

    /**
     * 获取账号二维码信息
     *
     * @param clientAccount 终端用户
     * @return 手机账户二维码信息
     */
    Response<AccountQrcodeMp> getAccountQrcode(ClientAccount clientAccount);

    /**
     * 生成头像上传地址
     *
     * @param clientAccount 终端用户
     * @return 头像上传地址
     */
    Response<PreSignedUrl> generateAvatarUrl(ClientAccount clientAccount);

    /**
     * 修改头像
     *
     * @param clientAccount 终端用户
     * @param avatar        头像
     * @return 操作结果
     */
    Response<Void> modifyAvatar(ClientAccount clientAccount, Map<String, String> avatar);

    /**
     * 修改昵称
     *
     * @param clientAccount 终端用户
     * @param nickname      昵称
     * @return 操作结果
     */
    Response<Void> modifyNickname(ClientAccount clientAccount, Map<String, String> nickname);

    /**
     * 修改签名简介
     *
     * @param clientAccount 终端用户
     * @param bio           签名简介
     * @return 操作结果
     */
    Response<Void> modifyBio(ClientAccount clientAccount, Map<String, String> bio);

    /**
     * 修改性别
     *
     * @param clientAccount 终端用户
     * @param gender        性别
     * @return 操作结果
     */
    Response<Void> modifyGender(ClientAccount clientAccount, Map<String, String> gender);

    /**
     * 修改生日
     *
     * @param clientAccount 终端用户
     * @param birthday      生日
     * @return 操作结果
     */
    Response<Void> modifyBirthday(ClientAccount clientAccount, Map<String, String> birthday);

    /**
     * 修改用车城市
     *
     * @param clientAccount 终端用户
     * @param city          用车城市
     * @return 操作结果
     */
    Response<Void> modifyCity(ClientAccount clientAccount, Map<String, String> city);

}
