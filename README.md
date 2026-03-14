# Soft Computing Library

A Java library implementing **Genetic Algorithms**, **Fuzzy Logic Systems**, and **Neural Networks**.

---

## Modules

### Genetic Algorithm

Strategy-based GA framework. All components are pluggable via interfaces.

| Category | Options |
|---|---|
| **Chromosomes** | `BinaryChromosome`, `FloatChromosome`, `IntegerChromosome`, `PermutationChromosome` |
| **Selection** | `RankSelection`, `RouletteWheelSelection` |
| **Crossover** | `NPointCrossOver`, `OrderOneCrossOver`, `UniformCrossOver` |
| **Mutation** | `FloatMutationStrategy`, `InsertMutationStrategy`, `InversionMutationStrategy` |
| **Replacement** | `ElitismReplacement`, `GenerationalReplacement`, `SteadyStateReplacement` |

```java
GeneticAlgorithmParameters params = new GeneticAlgorithmParameters(
    100,  // populationSize
    500,  // generations
    8,    // chromosomeLength
    0.8,  // crossoverRate
    0.05  // mutationRate
);

GeneralGeneticAlgorithm<Integer> ga = new GeneralGeneticAlgorithm<>(params);
IFitnessFunction<Integer> ff = new N_QueensCaseStudyFitnessFunction(8);

Chromosome<Integer> best = ga.runGeneticAlgorithm(
    ff,
    new RankSelection<>(),
    new OrderOneCrossOver<>(),
    new InsertMutationStrategy<>(),
    new ElitismReplacement<>(),
    new PermutationChromosome(8, ff),
    ch -> ff.evaluate(ch) == 0,   // stop condition
    true,                         // isMinimization
    2                             // parents to select
);
```

A ready-to-run **N-Queens** case study is available in `NQueensGeneticAlgorithmImplement`.

---

### Fuzzy Logic

Supports **Mamdani** (fuzzy outputs) and **Sugeno** (expression-based crisp outputs). Rules are loaded from JSON and evaluated with a recursive interpreter supporting `&`, `|`, `not`, and arbitrary nesting.

**Rule format (`mamdani.json`):**
```json
{
  "type": "MamdaniRule",
  "enabled": true,
  "condition": "((Study_Preparation is Excellent & Subject_Difficulty is Easy) | Sleep_Quality is Excellent)",
  "consequence": "Stress_Level is Low"
}
```

**Mamdani usage:**
```java
// Load rules
List<IRule> rules = new RuleEditor(new RuleStorage("rules.json")).getAll();

// Build input variables with membership functions
FuzzySet fs = new FuzzySet();
fs.addMemberFunction(new TrapzoidFunction("small",  List.of(0.0, 0.0, 20.0, 40.0), ...));
fs.addMemberFunction(new TrapzoidFunction("medium", List.of(20.0, 40.0, 60.0, 80.0), ...));

// Fuzzify → Infer → Defuzzify
IEngine engine = new MamdaniEngine();
engine.fuzzify(List.of(60.0, 25.0), List.of(new Variable("dirt", fs, 0, 100)));
Map<String, Set<Pair>> results = engine.inferRules(rules, null);

Pair output = new WeightAverageMean<>().defuzzify(outputFuzzySet, results);
```

> Inputs are auto-clamped to variable bounds. `null` or `NaN` values default to the midpoint.

For **Sugeno**, consequences are math expressions evaluated via exp4j:
```json
{ "consequence": "output = 2*Speed + 3*Load" }
```

**Member functions:** `TriangleFunction([a, b, c])`, `TrapzoidFunction([a, b, c, d])`

---

### Neural Network

Feedforward network with backpropagation, mini-batch training, and early stopping.

```java
NeuralNetwork nn = new NeuralNetwork(0.01, 200, 10, new CrossEntropy(), 0.001);
nn.addLayer(new NeuralLayer(5, 4, new ReluActivationFunction(),    new XavierInitializer()));
nn.addLayer(new NeuralLayer(3, 5, new SoftmaxActivationFunction(), new XavierInitializer()));

nn.train(X_train, Y_train);
ArrayList<ArrayList<Double>> preds = nn.predictBatch(X_test);
```

| Category | Options |
|---|---|
| **Activation** | `Sigmoid`, `ReLU`, `Tanh`, `Softmax`, `Linear` |
| **Loss** | `CrossEntropy`, `MeanSquaredErrorLoss` |
| **Weight Init** | `XavierInitializer`, `RandomInitializer` |

**Preprocessing pipeline:**
```java
table = new OneHotEncoding(table).encode();
List<Table> split = new TrainTestSplit(table).split(0.8);
NormalizeValues norm = new NormalizeValues(trainFeatures);
Table X_train = norm.normalize();
Table X_test  = norm.apply(testFeatures); // uses train statistics
```

A complete **Iris classification** case study (`iris_data.csv`) is included in `NN/CaseStudy/Main.java`.

---

## Diagrams

## Package Diagram
![GeneticAlgorithm Package Diagram](genetic_package_diagram.jpg)

## Class Diagram
![GeneticAlgorithm Class Diagram](genetic_class_diagram.jpg)

---

## Dependencies

| Module | Library | Version |
|---|---|---|
| Fuzzy Logic | `gson` | 2.11.0 |
| Fuzzy Logic | `exp4j` | 0.4.8 |
| Neural Network | `tablesaw-core` | 0.38.1 |
| Neural Network | `commons-math3` | 3.6.1 |
| Neural Network | `slf4j`, `guava`, `univocity-parsers` | various |

All JARs are bundled under `FuzzyLogic/lib/` and `NN/lib/`.
