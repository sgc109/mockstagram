import React from "react";
import "./Sidebar.css";

interface MenuItem {
  icon: string; // 아이콘 클래스 이름
  label: string; // 메뉴 항목 이름
}

const menuItems: MenuItem[] = [
  { icon: "fa fa-home", label: "Home" },
  { icon: "fa fa-search", label: "Search" },
  { icon: "fa fa-compass", label: "Explore" },
  { icon: "fa fa-film", label: "Reels" },
  { icon: "fa fa-paper-plane", label: "Messages" },
  { icon: "fa fa-heart", label: "Notifications" },
  { icon: "fa fa-plus-square", label: "Create" },
  { icon: "fa fa-circle", label: "Profile" },
];

const Sidebar: React.FC = () => {
  return (
    <div className="sidebar">
      {/* Mockstagram Logo */}
      <div className="sidebar__logo">Mockstagram</div>

      {/* Sidebar Menu */}
      <div className="sidebar__menu">
        {menuItems.map((item, index) => (
          <div className="sidebar__item" key={index}>
            <i className={item.icon}></i>
            <span>{item.label}</span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Sidebar;