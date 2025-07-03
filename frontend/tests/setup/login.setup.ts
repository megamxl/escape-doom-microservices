import { test as setup } from '@playwright/test';

setup('Login via Keycloak and save storage state', async ({ page }) => {
    if (process.env.NEXT_PUBLIC_WEB_URL == null) {
        throw new Error("NEXT_PUBLIC_WEB_URL not set - Can't run login setup for playwright")
    }
    const lectorDashboardPath = process.env.NEXT_PUBLIC_WEB_URL + "/lector-portal/dashboard"

    await page.goto(lectorDashboardPath); // Should trigger a redirect

    // Keycloak Login Page
    await page.fill('input#username', process.env.ADMIN_EMAIL || '');
    await page.fill('input#password', process.env.ADMIN_PASSWORD || '');
    await page.click('input#kc-login'); // Oder dein Login-Button

    await page.waitForURL(lectorDashboardPath); // oder ein Pfad nach Login

    await page.context().storageState({ path: 'playwright/.auth/user.json' });
});
