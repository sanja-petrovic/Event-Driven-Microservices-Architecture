import Button from '@/common/components/button/Button';
import { Register } from '@/model/Register';
import { register } from '@/services/auth.service';
import {
  HomeOutlined,
  LockOutlined,
  MailOutlined,
  PhoneOutlined,
  UserOutlined,
} from '@ant-design/icons';
import { Form, Input } from 'antd';
import { useRouter } from 'next/router';
import { useState } from 'react';
import styles from '../styles/auth.module.scss';

const SignUp = () => {
  const [form] = Form.useForm();
  const [passwordVisible, setPasswordVisible] = useState(false);
  const router = useRouter();

  const handleFinish = () => {
    register(form.getFieldsValue() as Register)
      .then((response) => router.push('/login'))
      .catch((error) => console.log(error));
  };

  return (
    <div className={styles.wrapper}>
      <h1 className={styles.title}>Sign up</h1>
      <Form form={form} className={styles.loginForm} onFinish={handleFinish}>
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
        <Button type="primary" text="Sign up" style={{ width: '100%' }} />
      </Form>
    </div>
  );
};

export default SignUp;
