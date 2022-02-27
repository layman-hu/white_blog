package com.white.dto;

import com.white.entity.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuDTO {

    private Integer id;
    private String menuName;
    private String url;
    private String icon;
    private Integer parentId;
    private boolean disabled;
    private List<MenuDTO> children = new ArrayList<>();

    public MenuDTO isParentMenu(Menu menu) {
        this.id = menu.getId();
        this.menuName = menu.getMenu();
        this.url = menu.getUrl();
        this.icon = menu.getIcon();
        this.parentId = menu.getParentId();
        this.disabled = menu.getDisabled();
        return this;
    }

    public MenuDTO isChildren(Menu menu) {
        this.id = menu.getId();
        this.menuName = menu.getMenu();
        this.url = menu.getUrl();
        this.icon = menu.getIcon();
        this.parentId = menu.getParentId();
        this.disabled = menu.getDisabled();
        return this;
    }
}
