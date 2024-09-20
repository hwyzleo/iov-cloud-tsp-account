package net.hwyz.iov.cloud.tsp.account.api.feign.mp;

import net.hwyz.iov.cloud.tsp.account.api.contract.AccountInfoMp;
import net.hwyz.iov.cloud.tsp.framework.commons.bean.ClientAccount;
import net.hwyz.iov.cloud.tsp.framework.commons.bean.Response;
import net.hwyz.iov.cloud.tsp.oss.api.contract.PreSignedUrl;

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
    Response<AccountInfoMp> getAccountInfo(ClientAccount clientAccount);

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
    Response<Void> modifyAvatar(ClientAccount clientAccount, String avatar);

    /**
     * 修改昵称
     *
     * @param clientAccount 终端用户
     * @param nickname      昵称
     * @return 操作结果
     */
    Response<Void> modifyNickname(ClientAccount clientAccount, String nickname);

    /**
     * 修改性别
     *
     * @param clientAccount 终端用户
     * @param gender        性别
     * @return 操作结果
     */
    Response<Void> modifyGender(ClientAccount clientAccount, String gender);

}
