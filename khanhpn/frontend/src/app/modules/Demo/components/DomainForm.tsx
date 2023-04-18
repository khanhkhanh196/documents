import React from "react";
import { Link } from "react-router-dom";
import ButtonLoading from "../../../components/Button/ButtonLoading";
import DomainType from "../DomainType";

interface Props {
  cancelLink: string,
  onSubmit: ((event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void) | undefined,
  setDomain: React.Dispatch<React.SetStateAction<DomainType | undefined>>,
  domain?: DomainType,
  errors?: DomainType
}

const DomainForm: React.FC<Props> = (props) => {
  const { domain, setDomain, errors, onSubmit, cancelLink } = props;

  return (
    <form className={"form-data row" + (errors ? " was-validated" : "")} noValidate>
      <div className="col-md-8">
        <div className="paper">
          <div className="row">
            <div className="col-md-12 mb-3">
              <label htmlFor="v1" className="input-required">
                Tên sản phẩm
              </label>
              <input
                type="text"
                className="form-control"
                id="v1"
                placeholder=""
                value={(domain && domain.name) || ""}
                onChange={(e) =>
                  setDomain({
                    ...domain,
                    name: e.target.value,
                  })
                }
                required
              />
            </div>
            <div className="col-md-12 mb-3">
              <label htmlFor="v2" className="input-required">Mã sản phẩm / SKU</label>
              <input
                type="text"
                className="form-control"
                id="v2"
                placeholder=""
                value={(domain && domain.code) || ""}
                onChange={(e) =>
                  setDomain({
                    ...domain,
                    code: e.target.value,
                  })
                }
                required
              />
            </div>
            <div className="col-md-12 mb-3">
              <label htmlFor="v2">Mô tả</label>
              <input
                type="text"
                className="form-control"
                id="v2"
                placeholder=""
                value={(domain && domain.description) || ""}
                onChange={(e) =>
                  setDomain({
                    ...domain,
                    code: e.target.value,
                  })
                }
              />
            </div>
          </div>
        </div>
        <div className="paper">
          <div className="row">
            <div className="col-md-12">
              <div className="d-flex align-items-center">
                <div>
                  <h3>Ảnh sản phẩm</h3>
                </div>
                <div className="mx-auto"></div>
                <div>
                  <div className="file">
                    <input type="file" className="input" id="file-image" />
                    <label className="label" htmlFor="file-image">
                      Thêm ảnh sản phẩm
                    </label>
                  </div>
                </div>
              </div>
            </div>
            <div className="col-md-12 mb-3"></div>
          </div>
        </div>
      </div>
      <div className="col-md-4">
        <div className="paper">
          <div className="row">
            <div className="col-md-12 mb-3">
              <label htmlFor="v3">Danh mục</label>
            </div>
          </div>
        </div>
      </div>
      <div className="col-md-12">
        <hr />
        <div className="d-flex">
          <div>
            <button type="button" className="btn btn-danger">
              Xóa sản phẩm
            </button>
          </div>
          <div className="mx-auto"></div>
          <div>
            <Link to={cancelLink}>
              <button type="button" className="btn btn-light mr-3">
                Hủy
              </button>
            </Link>
            <ButtonLoading
              type="button"
              className="btn btn-primary"
              onClick={onSubmit}
            >
              Lưu
            </ButtonLoading>
          </div>
        </div>
      </div>
    </form>
  );
}

export default DomainForm;