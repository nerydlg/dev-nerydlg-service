package dev.nerydlg.controller;

import dev.nerydlg.dto.Contact;
import dev.nerydlg.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/v1/contact")
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    public Page<Contact> getContacts(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
            @RequestHeader(value = "Host") String hostHeader) {
        Pageable pageable = PageRequest.of(page, size);
        return contactService.getContactInfo(hostHeader, pageable);
    }

}
