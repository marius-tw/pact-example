import { Pact } from "@pact-foundation/pact";
import * as path from "path";

interface ContractSpecification {
  providerName: string;
  consumerName: string;
}

export function createProvider(contractConfig: ContractSpecification): Pact {
  const provider = new Pact({
    provider: contractConfig.providerName,
    consumer: contractConfig.consumerName,
    logLevel: "debug",
    dir: path.resolve(process.cwd(), "contracts"),
    pactfileWriteMode: "overwrite",
    port: 8099,
  });
  beforeAll(() => provider.setup());
  afterEach(() => provider.verify());
  afterAll(() => provider.finalize());
  return provider;
}
