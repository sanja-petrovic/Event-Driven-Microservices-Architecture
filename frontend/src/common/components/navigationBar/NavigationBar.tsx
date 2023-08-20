import {
  IdcardOutlined,
  LockOutlined,
  LogoutOutlined,
  MailOutlined,
} from '@ant-design/icons';
import { MenuProps } from 'antd';
import Image from 'next/image';
import Link from 'next/link';
import { useRouter } from 'next/router';
import Logo from '../../../assets/logo.png';
import Button from '../button/Button';
import NavigationLink from '../navigationLink/NavigationLink';
import styles from './NavigationBar.module.scss';

const NavigationBar = () => {
  const router = useRouter();

  const items: MenuProps['items'] = [
    {
      key: '1',
      label: 'View profile',
      onClick: () => router.push('/profile'),
      icon: <IdcardOutlined />,
    },
    {
      key: '2',
      label: 'Change email',
      onClick: () => router.push('/email/change'),
      icon: <MailOutlined />,
    },
    {
      key: '3',
      label: 'Change password',
      onClick: () => router.push('/password/change'),
      icon: <LockOutlined />,
    },
    {
      type: 'divider',
    },
    {
      key: '4',
      label: 'Log out',
      icon: <LogoutOutlined />,
    },
  ];
  return (
    <div className={styles.wrapper}>
      <div className={styles.logo}>
        <Link href="/">
          <Image
            src={Logo}
            width={512}
            height={512}
            quality={100}
            alt="logo"
            style={{ maxWidth: '148px', height: 'auto' }}
          />
        </Link>
      </div>
      <div className={styles.links}>
        <NavigationLink href="/" text="Home" />
      </div>
      <div className={styles.buttons}>
        <Link href="/signup">
          <Button type="primary" text="Sign up" />
        </Link>
        <Link href="/login">
          <Button type="secondary" text="Log in" />
        </Link>
      </div>
    </div>
  );
};

export default NavigationBar;
