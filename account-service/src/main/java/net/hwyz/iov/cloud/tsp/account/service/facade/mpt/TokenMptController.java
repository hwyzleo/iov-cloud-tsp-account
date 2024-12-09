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
import net.hwyz.iov.cloud.tsp.account.api.contract.TokenMpt;
import net.hwyz.iov.cloud.tsp.account.api.feign.mpt.TokenMptApi;
import net.hwyz.iov.cloud.tsp.account.service.application.service.TokenAppService;
import net.hwyz.iov.cloud.tsp.account.service.facade.assembler.TokenMptAssembler;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po.TokenPo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 令牌相关管理后台接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mpt/token")
public class TokenMptController extends BaseController implements TokenMptApi {

    private final TokenAppService tokenAppService;

    /**
     * 分页查询令牌信息
     *
     * @param token      令牌信息
     * @param mptAccount 后台管理用户
     * @return 令牌信息列表
     */
    @RequiresPermissions("user:account:token:list")
    @Override
    @GetMapping(value = "/list")
    public TableDataInfo list(TokenMpt token, @RequestHeader(required = false) MptAccount mptAccount) {
        logger.info("管理后台用户[{}]分页查询令牌信息", ParamHelper.getMptAccountInfo(mptAccount));
        startPage();
        List<TokenPo> tokenPoList = tokenAppService.search(token.getAccountId(), token.getClientId(),
                token.getClientType(), token.getVin(), token.getAccessToken(), getBeginTime(token), getEndTime(token));
        List<TokenMpt> tokenMptList = TokenMptAssembler.INSTANCE.fromPoList(tokenPoList);
        return getDataTable(tokenPoList, tokenMptList);
    }

    /**
     * 导出令牌信息
     *
     * @param response   响应
     * @param token      令牌信息
     * @param mptAccount 后台管理用户
     */
    @Log(title = "令牌管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("user:account:token:export")
    @Override
    @PostMapping("/export")
    public void export(HttpServletResponse response, TokenMpt token, @RequestHeader(required = false) MptAccount mptAccount) {
        logger.info("管理后台用户[{}]导出令牌信息", ParamHelper.getMptAccountInfo(mptAccount));
        List<TokenPo> tokenPoList = tokenAppService.search(token.getAccountId(), token.getClientId(),
                token.getClientType(), token.getVin(), token.getAccessToken(), getBeginTime(token), getEndTime(token));
        List<TokenMpt> tokenMptList = TokenMptAssembler.INSTANCE.fromPoList(tokenPoList);
        ExcelUtil<TokenMpt> util = new ExcelUtil<>(TokenMpt.class);
        util.exportExcel(response, tokenMptList, "令牌数据");
    }
}