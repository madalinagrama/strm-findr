import React from "react";

const Dropdown = ({ title = "Dropdown", actions = [], callback = null }) => {
    const buttonId = title.toLowerCase().replace(/[^a-z0-9]+/gi, "-");

    const buttonProps = {
        className: "btn btn-danger dropdown-toggle w-100",
        type: "button",
        id: buttonId,
        "data-bs-toggle": "dropdown",
        "aria-expanded": "false",
    };

    return (
        <div className="dropdown">
            <button {...buttonProps}>{title}</button>
            <ul
                className="dropdown-menu w-100 dropdown-menu-dark"
                aria-labelledby={buttonId}
            >
                {actions.map((action, i) => (
                    <li key={i}>
                        <span
                            className="dropdown-item-text"
                            aria-current="page"
                            onClick={callback}
                            data-id={action.id}
                        >
                            {action.name}
                        </span>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Dropdown;
