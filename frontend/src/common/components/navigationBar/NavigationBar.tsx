import { logout } from '@/services/auth.service';
import Image from 'next/image';
import Link from 'next/link';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import Logo from '../../../assets/logo.png';
import Button from '../button/Button';
import styles from './NavigationBar.module.scss';

const NavigationBar = () => {
  const router = useRouter();
  const [authState, setAuthState] = useState<boolean>();

  useEffect(() => {
    const setAuth = () => {
      setAuthState(localStorage.getItem('jwt') !== undefined);
    };
    setAuth();
    window.addEventListener('storage', setAuth);

    return () => {
      window.removeEventListener('storage', setAuth);
    };
  }, []);

  const handleLogout = () => {
    logout()
      .then(() => {
        localStorage.clear();
        setAuthState(false);
      })
      .catch((error) => console.log(error));
  };

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
      <div className={styles.links}></div>
      <div className={styles.buttons}>
        {authState ? (
          <Button type="secondary" text="Log out" action={handleLogout} />
        ) : (
          <>
            <Link href="/signup">
              <Button type="primary" text="Sign up" />
            </Link>
            <Link href="/login">
              <Button type="secondary" text="Log in" />
            </Link>
          </>
        )}
      </div>
    </div>
  );
};

export default NavigationBar;
