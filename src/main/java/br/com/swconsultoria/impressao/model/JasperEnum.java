package br.com.swconsultoria.impressao.model;

import br.com.swconsultoria.impressao.util.ImpressaoUtil;
import net.sf.jasperreports.engine.JasperReport;

public enum JasperEnum {

    NFE("/nfe/danfe", true),
    NFE_FATURA("/nfe/danfe_fatura", false),
    NFCE("/nfce/danfce", false),
    CTE("/cte/dacte", false),
    CTE_PRESTACAO_SERVICO("/cte/subreport/DACTE_sub_prestacao_servico", false),
    CTE_DOC_NF("/cte/subreport/DACTE_sub_documentos_originarios_nf", false),
    CTE_DOC_NFE("/cte/subreport/DACTE_sub_documentos_originarios_nfe", false),
    CTE_DOC_OUTROS("/cte/subreport/DACTE_sub_documentos_originarios_outros", false),
    CTE_VEICULOS("/cte/subreport/DACTE_sub_veiculos_novos", false),
    MDFE("/mdfe/damdfe", false),
    CCE("/cce/cce", false);

    private final String caminho;
    private final boolean compilarJrxml;
    private volatile JasperReport jasper;

    JasperEnum(String caminho, boolean compilarJrxml) {
        this.caminho = caminho;
        this.compilarJrxml = compilarJrxml;
    }

    public JasperReport getJasper() {
        JasperReport jasperAtual = this.jasper;
        if (jasperAtual != null) {
            return jasperAtual;
        }

        synchronized (this) {
            if (this.jasper == null) {
                String extensao = this.compilarJrxml ? ".jrxml" : ".jasper";
                String caminhoRecurso = "/jasper" + caminho + extensao;
                this.jasper = this.compilarJrxml ? ImpressaoUtil.compilaJasperResources(caminhoRecurso)
                        : ImpressaoUtil.carregaJasperResources(caminhoRecurso);
            }

            return this.jasper;
        }
    }
}
