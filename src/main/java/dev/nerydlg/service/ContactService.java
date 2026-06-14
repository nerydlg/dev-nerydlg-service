package dev.nerydlg.service;

import dev.nerydlg.dto.Contact;
import dev.nerydlg.entity.NContact;
import dev.nerydlg.entity.NDomain;
import dev.nerydlg.mapper.ContactMapper;
import dev.nerydlg.repository.NContactRepository;
import dev.nerydlg.repository.NDomainRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactService {
    private final NContactRepository nContactRepository;
    private final ContactMapper contactMapper;


    public Page<Contact> getContactInfo(String hostHeader, Pageable Pageable) {
        String domain = hostHeader.contains(":") ?
                hostHeader.substring(0, hostHeader.indexOf(":"))
                : hostHeader;
        Page<NContact> contactsPage = nContactRepository.findByDomain_Name(domain, Pageable);
        return contactsPage.map(contactMapper::NContactToContact);
    }
}
