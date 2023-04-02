package com.kolay.reader.controller;

import com.kolay.reader.client.BookClient;
import com.kolay.reader.model.BookData;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReaderController {

    @Autowired
    BookClient bookClient;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession httpSession) {
        httpSession.removeAttribute("username");
        httpSession.removeAttribute("password");
        model.addAttribute("logout", true);
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(Model model, @RequestParam String username, @RequestParam String password, HttpSession httpSession) {
        httpSession.setAttribute("username", username);
        httpSession.setAttribute("password", password);
        try {
            BookData[] books = bookClient.getBooks();
            model.addAttribute("books", books);
            return "index";
        } catch (Exception e) {
            httpSession.removeAttribute("username");
            httpSession.removeAttribute("password");
            model.addAttribute("error", true);
            return "login";
        }
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null || "".equals(username)) {
            return "index";
        }
        try {
            BookData[] books = bookClient.getBooks();
            model.addAttribute("books", books);
            return "index";
        } catch (Exception e) {
            model.addAttribute("errormessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/book/{id}")
    public String readBook(Model model, HttpSession session, @PathVariable String id) {
        String username = (String) session.getAttribute("username");
        if (username == null || "".equals(username)) {
            return "index";
        }
        try {
            BookData book = bookClient.getBook(id);
            model.addAttribute("book", book);
            return "book";
        } catch (Exception e) {
            model.addAttribute("errormessage", e.getMessage());
            return "error";
        }
    }

}
