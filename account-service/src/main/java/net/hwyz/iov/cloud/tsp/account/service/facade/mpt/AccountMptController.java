package net.hwyz.iov.cloud.tsp.account.service.facade.mpt;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.audit.annotation.Log;
import net.hwyz.iov.cloud.framework.audit.enums.BusinessType;
import net.hwyz.iov.cloud.framework.common.bean.MptAccount;
import net.hwyz.iov.cloud.framework.common.util.ExcelUtil;
import net.hwyz.iov.cloud.framework.common.util.ParamHelper;
import net.hwyz.iov.cloud.framework.common.web.controller.BaseController;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.framework.security.annotation.RequiresPermissions;
import net.hwyz.iov.cloud.tsp.account.api.contract.AccountMpt;
import net.hwyz.iov.cloud.tsp.account.api.feign.mpt.AccountMptApi;
import net.hwyz.iov.cloud.tsp.account.service.application.service.AccountAppService;
import net.hwyz.iov.cloud.tsp.account.service.facade.assembler.AccountMptAssembler;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po.AccountPo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 账户相关管理后台接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mpt/account")
public class AccountMptController extends BaseController implements AccountMptApi {

    private final AccountAppService accountAppService;

    /**
     * 分页查询账号信息
     *
     * @param account    账号信息
     * @param mptAccount 后台管理用户
     * @return 账号信息列表
     */
    @RequiresPermissions("user:account:account:list")
    @Override
    @GetMapping(value = "/list")
    public TableDataInfo list(AccountMpt account, @RequestHeader(required = false) MptAccount mptAccount) {
        logger.info("管理后台用户[{}]分页查询账号信息", ParamHelper.getMptAccountInfo(mptAccount));
        startPage();
        List<AccountPo> accountPoList = accountAppService.search(account.getAccountId(), account.getMobile(),
                account.getRegSource(), account.getEnable(), getBeginTime(account), getEndTime(account));
        List<AccountMpt> accountMptList = AccountMptAssembler.INSTANCE.fromPoList(accountPoList);
        return getDataTable(accountMptList);
    }

    /**
     * 导出账号信息
     *
     * @param response   响应
     * @param account    账号信息
     * @param mptAccount 后台管理用户
     */
    @Log(title = "账号管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("user:account:account:export")
    @Override
    @PostMapping("/export")
    public void export(HttpServletResponse response, AccountMpt account, @RequestHeader(required = false) MptAccount mptAccount) {
        logger.info("管理后台用户[{}]导出账号信息", ParamHelper.getMptAccountInfo(mptAccount));
        List<AccountPo> accountPoList = accountAppService.search(account.getAccountId(), account.getMobile(),
                account.getRegSource(), account.getEnable(), getBeginTime(account), getEndTime(account));
        List<AccountMpt> accountMptList = AccountMptAssembler.INSTANCE.fromPoList(accountPoList);
        ExcelUtil<AccountMpt> util = new ExcelUtil<>(AccountMpt.class);
        util.exportExcel(response, accountMptList, "账号数据");
    }
}
