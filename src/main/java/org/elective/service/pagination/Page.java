package org.elective.service.pagination;

import org.apache.log4j.Logger;
import org.elective.command.commands.UsersGetCommand;

import javax.servlet.http.HttpServletRequest;

public class Page {
    public static final String ATTRIBUTE_PAGE_HOLDER = "page";
    public static final int OBJECTS_ON_PAGE = 4;
    private static final Logger log = Logger.getLogger(Page.class);

    private int page;
    private int count;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page < 1)
            page = 1;
        if (page > count)
            page = count;
        this.page = page;
    }

    public void setPage(HttpServletRequest request) {
        int extractedPage;
        try {
            String pageAsString = request.getParameter(ATTRIBUTE_PAGE_HOLDER);
            if (pageAsString == null) {
                extractedPage = 1;
                log.info("Page parameter absent. Page = 1");
            } else {
                extractedPage = Integer.parseInt(pageAsString);
            }
        } catch (NumberFormatException e) {
            log.error("Cant get page number: " + e.getMessage());
            extractedPage = 1;
        }
        setPage(extractedPage);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count < 1)
            count = 1;
        this.count = count;
    }

    public void calcPageCountFromObjectsCount(int objectsCount) {
        if (objectsCount < 1)
            objectsCount = 1;
        this.count = (objectsCount == OBJECTS_ON_PAGE)?
                objectsCount/OBJECTS_ON_PAGE:
                objectsCount/OBJECTS_ON_PAGE+1;
    }

    public Page(int page, int count) {
        this.page = page;
        this.count = count;
    }

    public Page() {
        this.page = 1;
        this.count = 1;
    }

    public Page(HttpServletRequest request, int count) {
        setCount(count);
        setPage(request);
    }
}
