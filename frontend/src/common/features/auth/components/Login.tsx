import { LockOutlined, MailOutlined } from '@ant-design/icons';
import { Form, Input } from 'antd';
import { useState } from 'react';
import styles from '../styles/auth.module.scss';

const Login = () => {
  const [form] = Form.useForm();
  const [passwordVisible, setPasswordVisible] = useState(false);
  return (
    <div className={styles.wrapper}>
      <h1 className={styles.title}>Log in</h1>
      <Form form={form} className={styles.loginForm}>
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
      </Form>
    </div>
  );
};

export default Login;
