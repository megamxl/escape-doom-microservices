import {expect, test} from '@playwright/test';
import {LECTOR_PORTAL_APP_PATHS} from "@/app/constants/paths.ts";

test('Create Escape Room without additional config', async ({ page }) => {
    await page.goto(LECTOR_PORTAL_APP_PATHS.DASHBOARD);

    const descText = 'Spukhaus mit versteckten Hinweisen'

    await page.getByRole('button', { name: 'Create new template' }).click();
    await page.getByRole('textbox', { name: 'Name' }).click();
    await page.getByRole('textbox', { name: 'Name' }).fill('Geistervilla');

    await page.getByRole('textbox', { name: 'Description' }).click();
    await page.getByRole('textbox', { name: 'Description' }).fill(descText);

    await page.getByRole('button', { name: 'Create' }).click();
    await page.getByRole('button', { name: 'Leave' }).click();
    await page.getByRole('button', { name: 'View Templates' }).click();

    const createdRoom = page.getByRole('heading', { name: 'Geistervilla' })

    expect(createdRoom.isVisible()).toBeTruthy()
});

test('Create levels and scenes', async ({ page }) => {
    await page.goto(LECTOR_PORTAL_APP_PATHS.TEMPLATE_VIEW);

    const toEdit = page.getByRole('button').filter({ hasText: /^$/ }).nth(1)
    await toEdit.first().click()

    await page.getByRole('button', { name: 'Add Level' }).click();
    await page.getByRole('heading', { name: 'New level' }).first().dblclick();
    await page.getByRole('textbox').press('ControlOrMeta+a');
    await page.getByRole('textbox').fill('Level 1');
    await page.getByRole('textbox').press('Enter');

    await page.getByRole('button', { name: 'Add Level' }).click();
    await page.getByRole('heading', { name: 'New level' }).dblclick();
    await page.getByRole('textbox').press('ControlOrMeta+a');
    await page.getByRole('textbox').fill('Level 2');
    await page.getByRole('textbox').press('Enter');

    await page.getByRole('button', { name: 'Add Scene' }).first().click();
    await page.getByRole('button', { name: 'New Scene' }).dblclick();
    await page.getByRole('textbox').press('ControlOrMeta+a');
    await page.getByRole('textbox').fill('Scene 1');
    await page.getByRole('textbox').press('Enter');

    await page.getByRole('button', { name: 'Add Scene' }).first().click();
    await page.getByRole('button', { name: 'New Scene' }).dblclick();
    await page.getByRole('textbox').press('ControlOrMeta+a');
    await page.getByRole('textbox').fill('Scene 2');
    await page.getByRole('textbox').press('Enter');

    await page.getByRole('button', { name: 'Add Scene' }).nth(1).click();
    await page.getByRole('button', { name: 'New Scene' }).dblclick();
    await page.getByRole('textbox').press('ControlOrMeta+a');
    await page.getByRole('textbox').fill('Scene 1');
    await page.getByRole('textbox').press('Enter');

    await page.getByRole('button', { name: 'Save' }).click();

    await expect(page.locator('body')).toContainText('Level 1');
    await expect(page.locator('body')).toContainText('Scene 1');
    await expect(page.locator('body')).toContainText('Scene 2');
    await expect(page.locator('body')).toContainText('Level 2');
});

test('Add Console Node to Scene', async ({ page }) => {
    await page.goto(LECTOR_PORTAL_APP_PATHS.TEMPLATE_VIEW);

    const toEdit = page.getByRole('button').filter({ hasText: /^$/ }).nth(1)
    await toEdit.first().click()

    await page.getByRole('heading', { name: 'Click to add image' }).click();
    await page.getByRole('button', { name: 'Click to add image' }).setInputFiles("./tests/resources/spooky-tree.jpg");

    const droppable = page.getByRole('img', { name: 'Scene Background' })
    const droppableBox = await droppable.boundingBox()

    if (!droppableBox?.x || !droppableBox?.y) throw new Error("Droppable box not found!");
    console.log('DroppableCoords: ', droppableBox.x, droppableBox.y)

    await page.locator('.p-2 > button:nth-child(2)')
        .dragTo(droppable, {
            targetPosition: {
                x: 50,
                y: 50,
            },
            force: true,
            timeout: 3000,
            trial: false
        })

    await page.waitForTimeout(500);

    await page.locator('.p-2 > button:nth-child(2)').click();
    await page.getByRole('textbox', { name: 'Title' }).click();
    await page.getByRole('textbox', { name: 'Title' }).fill('Test Input');
    await page.getByRole('textbox', { name: 'Title' }).press('Tab');

    await page.getByRole('textbox', { name: 'Description', exact: true }).fill('Test Description');
    await page.getByRole('textbox', { name: 'Description', exact: true }).press('Tab');

    await page.getByRole('textbox', { name: 'Return description' }).fill('Return Desc');
    await page.getByRole('textbox', { name: 'Return description' }).press('Tab');

    await page.getByRole('textbox', { name: 'Constraints' }).fill('Constraints');
    await page.getByRole('textbox', { name: 'Constraints' }).press('Tab');

    await page.getByRole('textbox', { name: 'Example' }).fill('Example');
    await page.getByRole('button', { name: 'Save' }).click();

    await expect(page.getByRole('textbox', { name: 'Title' })).toHaveValue('Test Input');
    await expect(page.getByRole('textbox', { name: 'Description', exact: true })).toHaveValue('Test Description');
    await expect(page.getByRole('textbox', { name: 'Return description' })).toHaveValue('Return Desc');
    await expect(page.getByRole('textbox', { name: 'Constraints' })).toHaveValue('Constraints');
    await expect(page.getByRole('textbox', { name: 'Example' })).toHaveValue('Example');
});