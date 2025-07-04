import { test as setup } from '@playwright/test';
import {LECTOR_PORTAL_APP_PATHS} from "@/app/constants/paths.ts";

setup('Login via Keycloak and save storage state', async ({ page }) => {

    await page.goto(LECTOR_PORTAL_APP_PATHS.DASHBOARD ?? "");

    // Keycloak Login Page
    await page.getByRole('textbox', { name: 'Username or email' }).click();
    await page.getByRole('textbox', { name: 'Username or email' }).fill('Spodo');
    await page.getByRole('textbox', { name: 'Username or email' }).press('Tab');

    await page.getByRole('textbox', { name: 'Password' }).fill('Spodo');
    await page.getByRole('button', { name: 'Sign In' }).click();

    await page.waitForURL(LECTOR_PORTAL_APP_PATHS.DASHBOARD);

    await page.context().storageState({ path: 'playwright/.auth/user.json' });
});
