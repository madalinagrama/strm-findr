const Input = ({
    type = "text",
    id,
    label,
    value,
    callback,
    required = true,
}) => (
    <div className="mb-3">
        <label className="form-label" htmlFor={id}>
            {label}:
        </label>
        <input
            className="form-control mb-3"
            type={type}
            id={id}
            name={id}
            placeholder={`Enter ${label}`}
            value={value}
            onChange={(e) => callback(e.target.value)}
            required={required}
        />
    </div>
);

export default Input;
