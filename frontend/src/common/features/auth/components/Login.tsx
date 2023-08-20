import Button from '@/common/components/button/Button';
import api from '@/common/utils/axiosInstance';
import { Login } from '@/model/Login';
import { login } from '@/services/auth.service';
import { LockOutlined, MailOutlined } from '@ant-design/icons';
import { Form, Input } from 'antd';
import { useRouter } from 'next/router';
import { useState } from 'react';
import styles from '../styles/auth.module.scss';

const Login = () => {
  const [form] = Form.useForm();
  const [passwordVisible, setPasswordVisible] = useState(false);
  const router = useRouter();
  const handleFinish = () => {
    console.log(form.getFieldsValue());
    login(form.getFieldsValue() as Login)
      .then((response) => {
        localStorage.setItem('jwt', response.data.jwt);
        api.defaults.headers.common.Authorization =
          'Bearer ' + response.data.jwt;
        router.push('/').then(() => router.reload());
      })
      .catch((error) => console.log(error));
  };
  return (
    <div className={styles.wrapper}>
      <h1 className={styles.title}>Log in</h1>
      <Form form={form} className={styles.loginForm} onFinish={handleFinish}>
        <Form.Item
          name="email"
          rules={[
            { required: true, message: 'Email is required.' },
            {
              type: 'email',
              message: 'Email is not valid.',
            },
          ]}
        >
          <Input
            className={styles.inputField}
            prefix={<MailOutlined />}
            placeholder="Email"
          />
        </Form.Item>
        <Form.Item
          name="password"
          rules={[{ required: true, message: 'Password is required.' }]}
        >
          <Input.Password
            className={styles.inputField}
            prefix={<LockOutlined />}
            type="password"
            placeholder="Password"
            visibilityToggle={{
              visible: passwordVisible,
              onVisibleChange: setPasswordVisible,
            }}
          />
        </Form.Item>
        <Button type="primary" text="Log in" style={{ width: '100%' }} />
      </Form>
    </div>
  );
};

export default Login;
