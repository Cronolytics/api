package com.cronolytics.api.service.subject;

import com.cronolytics.api.entity.Empresa;
import com.cronolytics.api.service.observer.RespondenteObserver;
import net.bytebuddy.agent.builder.AgentBuilder;
import java.util.List;

public class EmpresaSubject {

    private Empresa empresa;

    private List<PesquisaSubject> pesquisas;

    private List<RespondenteObserver> respondentes;


}
