package dev.nerydlg.service;

import dev.nerydlg.entity.NDomain;
import dev.nerydlg.repository.NDomainRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DomainService {

    private final NDomainRepository nDomainRepository;

    public void saveDomain(NDomain domain) {
        nDomainRepository.save(domain);
    }

    public NDomain getDomain(String name) {
        return nDomainRepository.findByName(name);
    }


}
