package dev.nerydlg.service;

import dev.nerydlg.configuration.ControllerConfiguration;
import dev.nerydlg.dto.BlogPost;
import dev.nerydlg.dto.Contact;
import dev.nerydlg.dto.PublicResponse;
import dev.nerydlg.entity.NBlog;
import dev.nerydlg.entity.NContact;
import dev.nerydlg.mapper.BlogMapper;
import dev.nerydlg.mapper.ContactMapper;
import dev.nerydlg.repository.NBlogRepository;
import dev.nerydlg.repository.NContactRepository;
import dev.nerydlg.repository.NDomainRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublicService {

    private final NBlogRepository nBlogRepository;
    private final BlogMapper blogMapper;
    private final NContactRepository nContactRepository;
    private final ContactMapper contactMapper;
    private final NDomainRepository nDomainRepository;

    public List<BlogPost> getMostRecentPosts(String lang, String hostHeader, Integer pageNum, Integer size) {
        Pageable pageable = PageRequest.of(pageNum, size);
        List<NBlog> posts = nBlogRepository.findTopPublishedByDomainNameAndLang(hostHeader, lang, pageable);
        return blogMapper.ListToBlogPostList(posts);
    }

    public BlogPost getPostByTitle(String title) {
        NBlog nBlog = nBlogRepository.findByTitle(title);
        return blogMapper.nBlogToBlogPost(nBlog);
    }

    public PublicResponse saveContact(Contact contact) {
        NContact nContact = contactMapper.ContactToNContact(contact);
        nContact.setDomain(nDomainRepository.findByName(contact.domain()));
        nContactRepository.save(nContact);
        if(nContact.getId() != null) {
            return new PublicResponse("OK", 0);
        } else {
            return new PublicResponse("FAIL", 99);
        }
    }
}
