import { open } from "sqlite";
import sqlite3 from "sqlite3";

import { createSchema } from "./schema";
import { getPendingOrders } from "./queries/order_queries";
import { sendSlackMessage } from "./slack";

const STALE_THRESHOLD_DAYS = 3;

async function main() {
  const db = await open({
    filename: "ecommerce.db",
    driver: sqlite3.Database,
  });

  await createSchema(db, false);

  const pendingOrders = await getPendingOrders(db);
  const staleOrders = pendingOrders.filter(
    (order) => order.days_since_created > STALE_THRESHOLD_DAYS,
  );

  if (staleOrders.length === 0) {
    return;
  }

  const lines = staleOrders.map(
    (order) =>
      `- Order #${order.order_id}: ${order.customer_name} (${order.phone || "no phone"}) — pending ${Math.floor(order.days_since_created)} days, $${order.total_amount}`,
  );

  const text = `*${staleOrders.length} order(s) pending more than ${STALE_THRESHOLD_DAYS} days:*\n${lines.join("\n")}`;

  await sendSlackMessage("#order-alerts", text);
}

main();
