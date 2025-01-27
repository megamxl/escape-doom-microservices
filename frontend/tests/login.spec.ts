import {expect, test} from '@playwright/test';
import {LECTOR_PORTAL_APP_PATHS} from "@/app/constants/paths";

const LOGIN_NAMES = [
    {name: 'leon@doom.at', pw: 'escapeDoom'},
    {name: 'raccoon@doom.at', pw: 'iLikeTrash<3'},
]

test.beforeEach(async ({page}) => {
    await page.goto(LECTOR_PORTAL_APP_PATHS.LOGIN)
})

test.describe('Login', () => {

    test.use({ storageState: undefined })
    test('should redirect to correct URL after login', async ({page}) => {
        const email_in = page.getByLabel('Email Address')
        const pw_in = page.getByLabel('Password')

        const user = LOGIN_NAMES[0];
        await email_in.fill(user.name);
        await pw_in.fill(user.pw);

        // Submits form
        await page.getByRole("button", {name: "Log In"}).click()

        await page.waitForURL(LECTOR_PORTAL_APP_PATHS.DASHBOARD);

        expect(page.url()).toBe(LECTOR_PORTAL_APP_PATHS.DASHBOARD);
    });

    test.use({ storageState: undefined })
    test('should not redirect as login fails', async ({page}) => {
        await page.goto(LECTOR_PORTAL_APP_PATHS.LOGIN)

        const email_in = page.getByLabel('Email Address')
        const pw_in = page.getByLabel('Password')

        const user = LOGIN_NAMES[1];
        await email_in.fill(user.name);
        await pw_in.fill(user.pw);

        // Submits form
        await page.getByRole("button", {name: "Log In"}).click()

        expect(page.url()).toBe(LECTOR_PORTAL_APP_PATHS.LOGIN);
    });
})
