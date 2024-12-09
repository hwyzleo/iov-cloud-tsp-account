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
import net.hwyz.iov.cloud.tsp.account.api.contract.ClientMpt;
import net.hwyz.iov.cloud.tsp.account.api.feign.mpt.ClientMptApi;
import net.hwyz.iov.cloud.tsp.account.service.application.service.ClientAppService;
import net.hwyz.iov.cloud.tsp.account.service.facade.assembler.ClientMptAssembler;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po.ClientPo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户端相关管理后台接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mpt/client")
public class ClientMptController extends BaseController implements ClientMptApi {

    private final ClientAppService clientAppService;

    /**
     * 分页查询客户端信息
     *
     * @param client     客户端信息
     * @param mptAccount 后台管理用户
     * @return 客户端信息列表
     */
    @RequiresPermissions("customer:account:client:list")
    @Override
    @GetMapping(value = "/list")
    public TableDataInfo list(ClientMpt client, @RequestHeader(required = false) MptAccount mptAccount) {
        logger.info("管理后台用户[{}]分页查询客户端信息", ParamHelper.getMptAccountInfo(mptAccount));
        startPage();
        List<ClientPo> clientPoList = clientAppService.search(client.getAccountId(), client.getClientId(),
                client.getClientType(), getBeginTime(client), getEndTime(client));
        List<ClientMpt> clientMptList = ClientMptAssembler.INSTANCE.fromPoList(clientPoList);
        return getDataTable(clientPoList, clientMptList);
    }

    /**
     * 导出客户端信息
     *
     * @param response   响应
     * @param client     客户端信息
     * @param mptAccount 后台管理用户
     */
    @Log(title = "客户端管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("customer:account:client:export")
    @Override
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClientMpt client, @RequestHeader(required = false) MptAccount mptAccount) {
        logger.info("管理后台用户[{}]导出客户端信息", ParamHelper.getMptAccountInfo(mptAccount));
        List<ClientPo> clientPoList = clientAppService.search(client.getAccountId(), client.getClientId(),
                client.getClientType(), getBeginTime(client), getEndTime(client));
        List<ClientMpt> clientMptList = ClientMptAssembler.INSTANCE.fromPoList(clientPoList);
        ExcelUtil<ClientMpt> util = new ExcelUtil<>(ClientMpt.class);
        util.exportExcel(response, clientMptList, "客户端数据");
    }
}