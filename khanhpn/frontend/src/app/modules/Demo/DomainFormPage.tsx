import React, { useState, useCallback, useEffect, useContext } from "react";
import { RouteComponentProps } from 'react-router';
import HeadPage from "../../components/HeadPage/Header";
import DomainForm from "./components/DomainForm";
import FakeService from "../../../services/FakeService";
import AppContext from "../../contexts/AppContext";
import DomainType from "./DomainType";

const baseURL = "demo";
const service = new FakeService();

interface MatchParams {
  name: string;
  id?: string;
}
interface Props extends RouteComponentProps<MatchParams> {
}
const DomainFormPage: React.FC<Props> = (props) => {
  const appCtx = useContext(AppContext);
  const { openNotifier, levels } = appCtx;
  const {
    match: { params },
  } = props;

  const [titlePage, setTitlePage] = useState("Thêm mới domain");
  const [domain, setDomain] = useState<DomainType>();
  const [errors, setErrors] = useState<DomainType>();
  const [notFound, setNotFound] = useState<boolean>(false);
  const [readySave, setReadySave] = useState<boolean>(false);

  const loadDomain = useCallback(async () => {
    if (!params.id) {
      setTitlePage("Thêm mới domain");
      return;
    }

    if (isNaN(+params.id)) {
      setNotFound(true);
      return;
    }

    let response = await service.getOne(+params.id);
    if (!response || response.status !== 200 || !response.data) {
      setNotFound(true);
      return;
    }
    const domainData = response.data;
    setDomain(domainData);
    setTitlePage(`[${domainData.id}] ${domainData.name}`);
  }, [params]);

  const validate = (domain?: DomainType) => {
    setErrors(undefined);
    let errors = undefined;
    if (!domain) {
      return false;
    }
    const { name, code } = domain;

    if (!name) {
      errors = {
        name: "Tên không được để trống",
      };
    }
    if (name && name.length > 255) {
      errors = {
        ...errors,
        name: "Tên tối đa 255 kí tự",
      };
    }

    if (!code) {
      errors = {
        ...errors,
        code: "Mã sản phẩm không được để trống",
      };
    }
    if (code && code.length > 255) {
      errors = {
        ...errors,
        code: "Mã sản phẩm tối đa 255 kí tự",
      };
    }

    if (errors) {
      setErrors(errors);
    }
    return errors ? false : true;
  };

  const save = useCallback(async () => {
    if (!validate(domain)) {
      console.log("valid form")
      return;
    }
    if (!domain) {
      return;
    }

    const { id } = domain;
    const response = await service.save(domain);

    let level = levels.success;
    let message = "Thêm mới sản phẩm thành công";
    let isCreate = true;
    if (id) {
      isCreate = false;
      message = "Cập nhật sản phẩm thành công";
    }

    if (!response || !response.data) {
      message = "Lỗi hệ thống";
      level = levels.warn;
    }

    if (
      response &&
      response.status !== 200 &&
      response.status !== 201 &&
      response.data
    ) {
      message = response.data.message;
      level = levels.warn;
    }

    openNotifier(true, message, level);
    if (level === levels.success && response.data) {
      if (isCreate) {
        props.history.push(`/${baseURL}/${response.data.id}`);
      } else {
        loadDomain();
      }
    }
  }, [domain, openNotifier, levels, props.history, loadDomain]);

  useEffect(() => {
    loadDomain();
  }, [loadDomain]);

  useEffect(() => {
    if (!readySave) {
      return;
    }
    setReadySave(false);
    save();
  }, [readySave, save]);

  if (notFound) {
    return (
      <div className="text-center mt-5">
        <h1>Sản phẩm không tồn tại!</h1>
      </div>
    );
  }

  return (
    <div className="container">
      <HeadPage
        title={titlePage}
        backBtn={{ label: "Quay lại trang danh sách", link: `/${baseURL}` }}
      />
      <DomainForm
        domain={domain}
        setDomain={setDomain}
        errors={errors}
        cancelLink={`/${baseURL}`}
        onSubmit={() => {
          setReadySave(true);
        }}
      />
    </div>
  );
}

export default DomainFormPage;