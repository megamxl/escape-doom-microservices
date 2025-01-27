import {test as setup} from '@playwright/test';
import {LECTOR_PORTAL_API} from "@/app/constants/paths";

const authFile = 'playwright/.auth/user.json';

const LOGIN = { name: 'leon@doom.at', pw: 'escapeDoom' }

setup('authenticate', async ({ request }) => {
    await request.post(LECTOR_PORTAL_API.AUTH, {
        form: {
            'email': LOGIN.name,
            'password': LOGIN.pw
        }
    });
    await request.storageState({ path: authFile });
});