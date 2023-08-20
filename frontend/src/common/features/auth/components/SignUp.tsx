import {
  HomeOutlined,
  LockOutlined,
  MailOutlined,
  PhoneOutlined,
  UserOutlined,
} from '@ant-design/icons';
import { Form, Input } from 'antd';
import { useState } from 'react';
import styles from '../styles/auth.module.scss';

const SignUp = () => {
  const [form] = Form.useForm();
  const [passwordVisible, setPasswordVisible] = useState(false);
  return (
    <div className={styles.wrapper}>
      <h1 className={styles.title}>Sign up</h1>
      <Form form={form} className={styles.loginForm}>
        <Form.Item
          name="name"
          rules={[{ required: true, message: 'Name is required.' }]}
        >
          <Input
            className={styles.inputField}
            prefix={<UserOutlined />}
            placeholder="Name"
          />
        </Form.Item>
        <Form.Item
          name="address"
          rules={[{ required: true, message: 'Address is required.' }]}
        >
          <Input
            className={styles.inputField}
            prefix={<HomeOutlined />}
            placeholder="Address"
          />
        </Form.Item>
        <Form.Item
          name="phone"
          rules={[{ required: true, message: 'Phone is required.' }]}
        >
          <Input
            className={styles.inputField}
            prefix={<PhoneOutlined />}
            placeholder="Phone"
          />
        </Form.Item>
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

export default SignUp;
