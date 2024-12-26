import React from "react";
import "./Sidebar.css";
import {Link} from "react-router-dom";

interface MenuItem {
    icon: string; // 아이콘 클래스 이름
    label: string; // 메뉴 항목 이름
    onClick?: () => void; // 클릭 이벤트 핸들러
    link?: string; // 클릭 시 이동할 링크
}

interface SidebarProps {
    openPostUploadDialog: () => void;
}

const Sidebar: React.FC<SidebarProps> = ({openPostUploadDialog}) => {
    const menuItems: MenuItem[] = [
        {icon: "fa fa-home", label: "Home", link: "/"},
        {icon: "fa fa-search", label: "Search"},
        {icon: "fa fa-compass", label: "Explore"},
        {icon: "fa fa-film", label: "Reels"},
        {icon: "fa fa-paper-plane", label: "Messages"},
        {icon: "fa fa-heart", label: "Notifications"},
        {icon: "fa fa-plus-square", label: "Create", onClick: openPostUploadDialog},
        {icon: "fa fa-circle", label: "Profile", link: `/${process.env.REACT_APP_LOGGEDIN_USERNAME}`},
    ];

    return (
        <div className="sidebar">
            {/* Mockstagram Logo */}
            <Link to="/" style={{textDecoration: 'none', color: 'inherit'}}>
                <div className="sidebar__logo">Mockstagram</div>
            </Link>

            {/* Sidebar Menu */}
            <div className="sidebar__menu">
                {menuItems.map((item, index) => item.link ? (
                    <Link to={item.link} style={{textDecoration: 'none'}}>
                        <div className="sidebar__item" key={index} onClick={item.onClick}>
                            <i className={item.icon}></i>
                            <span>{item.label}</span>
                        </div>
                    </Link>
                ) : (
                    <div className="sidebar__item" key={index} onClick={item.onClick}>
                        <i className={item.icon}></i>
                        <span>{item.label}</span>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Sidebar;
