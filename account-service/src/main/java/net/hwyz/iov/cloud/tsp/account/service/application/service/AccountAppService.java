package net.hwyz.iov.cloud.tsp.account.service.application.service;

import lombok.RequiredArgsConstructor;
import net.hwyz.iov.cloud.tsp.account.api.contract.AccountInfo;
import net.hwyz.iov.cloud.tsp.account.api.contract.AccountInfoMp;
import net.hwyz.iov.cloud.tsp.account.service.domain.account.model.AccountDo;
import net.hwyz.iov.cloud.tsp.account.service.domain.account.repository.AccountRepository;
import net.hwyz.iov.cloud.tsp.account.service.domain.account.service.AccountService;
import net.hwyz.iov.cloud.tsp.account.service.domain.external.service.ExObjectService;
import net.hwyz.iov.cloud.tsp.account.service.facade.assembler.AccountInfoAssembler;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception.AccountNotExistException;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.Gender;
import net.hwyz.iov.cloud.tsp.oss.api.contract.PreSignedUrl;
import net.hwyz.iov.cloud.tsp.oss.api.contract.enums.ObjectAccessPermission;
import net.hwyz.iov.cloud.tsp.oss.api.contract.request.GeneratePreSignedUrlRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 账号应用服务类
 *
 * @author hwyz_leo
 */
@Service
@RequiredArgsConstructor
public class AccountAppService {

    final AccountService accountService;
    final ExObjectService objectService;
    final AccountRepository accountRepository;

    @Value("${spring.application.name}")
    private String serviceName;

    /**
     * 获取手机端账号信息
     *
     * @param accountId 账号唯一ID
     * @return 手机端账号信息
     */
    public AccountInfoMp getMpAccountInfo(String accountId) {
        return accountService.get(accountId).map(accountDo -> AccountInfoMp.builder()
                .mobile(accountDo.getMobile())
                .avatar(accountDo.getAvatar())
                .nickname(accountDo.getNickname())
                .gender(accountDo.getGender().name())
                .build()).orElse(null);
    }

    /**
     * 获取账号信息
     * @param accountId 账号ID
     * @return 账号信息
     */
    public AccountInfo getAccountInfo(String accountId) {
        return accountService.get(accountId).map(accountDo -> {
            return AccountInfoAssembler.INSTANCE.fromDo(accountDo);
        }).orElse(null);
    }

    /**
     * 生成用户头像上传地址
     *
     * @param uid 账号唯一ID
     * @return 头像上传地址
     */
    public PreSignedUrl generateAvatarUrl(String uid) {
        GeneratePreSignedUrlRequest request = GeneratePreSignedUrlRequest.builder()
                .serviceName(serviceName)
                .objectName("avatar-" + uid + ".jpeg")
                .permission(ObjectAccessPermission.PUBLIC_READ_PRIVATE_WRITE)
                .build();
        return objectService.generatePreSignedUrl(request);
    }

    /**
     * 修改手机端账号信息
     *
     * @param uid     账号唯一ID
     * @param infoMap 信息Map
     */
    public void modifyMpAccountInfo(String uid, Map<String, String> infoMap) {
        AccountDo accountDo = accountService.get(uid).orElseThrow(() -> new AccountNotExistException(uid));
        if (infoMap.containsKey("avatar")) {
            accountDo.modifyAvatar(infoMap.get("avatar"));
        }
        if (infoMap.containsKey("nickname")) {
            accountDo.modifyNickname(infoMap.get("nickname"));
        }
        if (infoMap.containsKey("gender")) {
            accountDo.modifyGender(Gender.valueOf(infoMap.get("gender")));
        }
        accountRepository.save(accountDo);
    }

}
