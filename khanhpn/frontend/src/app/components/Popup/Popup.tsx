import React from "react";
import "./style/Popup.css";

type Props = {
  show?: boolean,
  body?: React.ReactNode
  buttonSubmit: { label?: string, onClick: Function, classname?: string }
  onClose: Function
  title?: string,
}

const Popup: React.FC<Props> = (props) => {
  const { buttonSubmit, body, title, show, onClose } = props;

  const hanleClickSubmit = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    e.preventDefault();
    buttonSubmit.onClick();
    onClose();
  };

  return (
    <div className={`modal fade ${show ? "d-block show" : "d-none"}`}>
      <div className="modal-dialog modal-dialog-new">
        <div className="modal-content">
          <form>
            <div className="modal-header">
              <span className="ui-dialog-title">{title}</span>
              <span
                className="modal-close-wrapper btn btn-close"
                role="button"
                aria-label="Close dialog"
                onClick={() => onClose()}
              >
                x
            </span>
            </div>
            <div className="modal-body">{body}</div>
            <div className="modal-footer">
              <div>
                <button className="btn btn-light" onClick={() => onClose()}>
                  Thoát
              </button>
                {buttonSubmit && (
                  <button
                    className={"ml-3 btn " + (buttonSubmit.classname || "")}
                    onClick={(e) => hanleClickSubmit(e)}
                    type="submit"
                  >
                    {buttonSubmit.label}
                  </button>
                )}
              </div>
            </div>
          </form>

        </div>
      </div>
    </div>
  );
};

export default Popup;
