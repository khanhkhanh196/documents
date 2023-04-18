type ColumnType = {
    field: string | number,
    label?: string
    align?: "left" | "center" | "right" | "justify" | "char",
    minWidth?: number
    type?: "link" | "date" | "image",
    format?: string,
};

export default ColumnType;