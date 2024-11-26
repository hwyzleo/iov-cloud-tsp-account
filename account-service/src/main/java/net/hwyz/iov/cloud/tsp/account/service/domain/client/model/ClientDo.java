package net.hwyz.iov.cloud.tsp.account.service.domain.client.model;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import net.hwyz.iov.cloud.framework.common.domain.BaseDo;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.framework.common.enums.Os;
import net.hwyz.iov.cloud.tsp.account.service.domain.contract.enums.ClientOperation;

import java.util.Date;

/**
 * 客户端领域对象
 *
 * @author hwyz_leo
 */
@Getter
@SuperBuilder
public class ClientDo extends BaseDo<Long> {

    /**
     * 账号唯一ID
     */
    private String accountId;
    /**
     * 推送注册ID
     */
    private String pushRegId;
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 客户端类型
     */
    private ClientType clientType;
    /**
     * 操作系统
     */
    private Os os;
    /**
     * 最后登录时间
     */
    private Date loginTime;

    /**
     * 初始化
     */
    public void init() {
        stateInit();
    }

    /**
     * 检查客户端操作
     *
     * @param operation 客户端操作
     */
    public void checkOperation(ClientOperation operation) {
        // TODO 可增加策略动态限制
        // 现在先不限制
    }

    /**
     * 客户端登录
     *
     * @param accountId 账号唯一ID
     */
    public void login(String accountId) {
        this.accountId = accountId;
        this.loginTime = new Date();
        stateChange();
    }

    /**
     * 更新推送注册ID
     *
     * @param pushRegId 推送注册ID
     */
    public void updatePushRegId(String pushRegId) {
        if (StrUtil.isNotBlank(pushRegId) && !pushRegId.equals(this.pushRegId)) {
            this.pushRegId = pushRegId;
            stateChange();
        }
    }

    /**
     * 更新操作系统
     *
     * @param os 操作系统
     */
    public void updateOs(String os) {
        if (StrUtil.isNotBlank(os) && Os.valueOf(os) != this.os) {
            this.os = Os.valueOf(os);
            stateChange();
        }
    }

}
