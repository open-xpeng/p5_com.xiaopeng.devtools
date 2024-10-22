package android.support.constraint.solver;

import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public class LinearSystem {
    private static final boolean DEBUG = false;
    private static int POOL_SIZE = 1000;
    final Cache mCache;
    private ArrayRow[] mRows;
    int mVariablesID = 0;
    private HashMap<String, SolverVariable> mVariables = null;
    private Goal mGoal = new Goal();
    private int TABLE_SIZE = 32;
    private int mMaxColumns = this.TABLE_SIZE;
    private boolean[] mAlreadyTestedCandidates = new boolean[this.TABLE_SIZE];
    int mNumColumns = 1;
    private int mNumRows = 0;
    private int mMaxRows = this.TABLE_SIZE;
    private SolverVariable[] mPoolVariables = new SolverVariable[POOL_SIZE];
    private int mPoolVariablesCount = 0;
    private ArrayRow[] tempClientsCopy = new ArrayRow[this.TABLE_SIZE];

    public LinearSystem() {
        this.mRows = null;
        this.mRows = new ArrayRow[this.TABLE_SIZE];
        releaseRows();
        this.mCache = new Cache();
    }

    private void increaseTableSize() {
        this.TABLE_SIZE *= 2;
        this.mRows = (ArrayRow[]) Arrays.copyOf(this.mRows, this.TABLE_SIZE);
        this.mCache.mIndexedVariables = (SolverVariable[]) Arrays.copyOf(this.mCache.mIndexedVariables, this.TABLE_SIZE);
        this.mAlreadyTestedCandidates = new boolean[this.TABLE_SIZE];
        this.mMaxColumns = this.TABLE_SIZE;
        this.mMaxRows = this.TABLE_SIZE;
        this.mGoal.variables.clear();
    }

    private void releaseRows() {
        for (int i = 0; i < this.mRows.length; i++) {
            ArrayRow arrayRow = this.mRows[i];
            if (arrayRow != null) {
                this.mCache.arrayRowPool.release(arrayRow);
            }
            this.mRows[i] = null;
        }
    }

    public void reset() {
        for (int i = 0; i < this.mCache.mIndexedVariables.length; i++) {
            SolverVariable solverVariable = this.mCache.mIndexedVariables[i];
            if (solverVariable != null) {
                solverVariable.reset();
            }
        }
        this.mCache.solverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.mCache.mIndexedVariables, (Object) null);
        if (this.mVariables != null) {
            this.mVariables.clear();
        }
        this.mVariablesID = 0;
        this.mGoal.variables.clear();
        this.mNumColumns = 1;
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            this.mRows[i2].used = false;
        }
        releaseRows();
        this.mNumRows = 0;
    }

    public SolverVariable createObjectVariable(Object obj) {
        SolverVariable solverVariable = null;
        if (obj == null) {
            return null;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariable = constraintAnchor.getSolverVariable();
            if (solverVariable == null) {
                constraintAnchor.resetSolverVariable(this.mCache);
                solverVariable = constraintAnchor.getSolverVariable();
            }
            if (solverVariable.id == -1 || solverVariable.id > this.mVariablesID || this.mCache.mIndexedVariables[solverVariable.id] == null) {
                if (solverVariable.id != -1) {
                    solverVariable.reset();
                }
                this.mVariablesID++;
                this.mNumColumns++;
                solverVariable.id = this.mVariablesID;
                solverVariable.mType = SolverVariable.Type.UNRESTRICTED;
                this.mCache.mIndexedVariables[this.mVariablesID] = solverVariable;
            }
        }
        return solverVariable;
    }

    public ArrayRow createRow() {
        ArrayRow acquire = this.mCache.arrayRowPool.acquire();
        if (acquire == null) {
            return new ArrayRow(this.mCache);
        }
        acquire.reset();
        return acquire;
    }

    public SolverVariable createSlackVariable() {
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK);
        this.mVariablesID++;
        this.mNumColumns++;
        acquireSolverVariable.id = this.mVariablesID;
        this.mCache.mIndexedVariables[this.mVariablesID] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    private void addError(ArrayRow arrayRow) {
        arrayRow.addError(createErrorVariable(), createErrorVariable());
    }

    private void addSingleError(ArrayRow arrayRow, int i) {
        arrayRow.addSingleError(createErrorVariable(), i);
    }

    private SolverVariable createVariable(String str, SolverVariable.Type type) {
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(type);
        acquireSolverVariable.setName(str);
        this.mVariablesID++;
        this.mNumColumns++;
        acquireSolverVariable.id = this.mVariablesID;
        if (this.mVariables == null) {
            this.mVariables = new HashMap<>();
        }
        this.mVariables.put(str, acquireSolverVariable);
        this.mCache.mIndexedVariables[this.mVariablesID] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    public SolverVariable createErrorVariable() {
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.ERROR);
        this.mVariablesID++;
        this.mNumColumns++;
        acquireSolverVariable.id = this.mVariablesID;
        this.mCache.mIndexedVariables[this.mVariablesID] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    private SolverVariable acquireSolverVariable(SolverVariable.Type type) {
        SolverVariable acquire = this.mCache.solverVariablePool.acquire();
        if (acquire == null) {
            acquire = new SolverVariable(type);
        } else {
            acquire.reset();
            acquire.setType(type);
        }
        if (this.mPoolVariablesCount >= POOL_SIZE) {
            POOL_SIZE *= 2;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, POOL_SIZE);
        }
        SolverVariable[] solverVariableArr = this.mPoolVariables;
        int i = this.mPoolVariablesCount;
        this.mPoolVariablesCount = i + 1;
        solverVariableArr[i] = acquire;
        return acquire;
    }

    Goal getGoal() {
        return this.mGoal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayRow getRow(int i) {
        return this.mRows[i];
    }

    float getValueFor(String str) {
        SolverVariable variable = getVariable(str, SolverVariable.Type.UNRESTRICTED);
        if (variable == null) {
            return 0.0f;
        }
        return variable.computedValue;
    }

    public int getObjectVariableValue(Object obj) {
        SolverVariable solverVariable = ((ConstraintAnchor) obj).getSolverVariable();
        if (solverVariable != null) {
            return (int) (solverVariable.computedValue + 0.5f);
        }
        return 0;
    }

    SolverVariable getVariable(String str, SolverVariable.Type type) {
        if (this.mVariables == null) {
            this.mVariables = new HashMap<>();
        }
        SolverVariable solverVariable = this.mVariables.get(str);
        if (solverVariable == null) {
            return createVariable(str, type);
        }
        return solverVariable;
    }

    void rebuildGoalFromErrors() {
        this.mGoal.updateFromSystem(this);
    }

    public void minimize() throws Exception {
        minimizeGoal(this.mGoal);
    }

    void minimizeGoal(Goal goal) throws Exception {
        goal.updateFromSystem(this);
        enforceBFS(goal);
        optimize(goal);
        computeValues();
    }

    private void updateRowFromVariables(ArrayRow arrayRow) {
        if (this.mNumRows > 0) {
            arrayRow.variables.updateFromSystem(arrayRow, this.mRows);
            if (arrayRow.variables.currentSize == 0) {
                arrayRow.isSimpleDefinition = true;
            }
        }
    }

    public void addConstraint(ArrayRow arrayRow) {
        if (arrayRow == null) {
            return;
        }
        if (this.mNumRows + 1 >= this.mMaxRows || this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        if (!arrayRow.isSimpleDefinition) {
            updateRowFromVariables(arrayRow);
            arrayRow.ensurePositiveConstant();
            arrayRow.pickRowVariable();
            if (!arrayRow.hasKeyVariable()) {
                return;
            }
        }
        if (this.mRows[this.mNumRows] != null) {
            this.mCache.arrayRowPool.release(this.mRows[this.mNumRows]);
        }
        if (!arrayRow.isSimpleDefinition) {
            arrayRow.updateClientEquations();
        }
        this.mRows[this.mNumRows] = arrayRow;
        arrayRow.variable.definitionId = this.mNumRows;
        this.mNumRows++;
        int i = arrayRow.variable.mClientEquationsCount;
        if (i > 0) {
            while (this.tempClientsCopy.length < i) {
                this.tempClientsCopy = new ArrayRow[this.tempClientsCopy.length * 2];
            }
            ArrayRow[] arrayRowArr = this.tempClientsCopy;
            for (int i2 = 0; i2 < i; i2++) {
                arrayRowArr[i2] = arrayRow.variable.mClientEquations[i2];
            }
            for (int i3 = 0; i3 < i; i3++) {
                ArrayRow arrayRow2 = arrayRowArr[i3];
                if (arrayRow2 != arrayRow) {
                    arrayRow2.variables.updateFromRow(arrayRow2, arrayRow);
                    arrayRow2.updateClientEquations();
                }
            }
        }
    }

    private int optimize(Goal goal) {
        for (int i = 0; i < this.mNumColumns; i++) {
            this.mAlreadyTestedCandidates[i] = false;
        }
        boolean z = false;
        int i2 = 0;
        int i3 = 0;
        while (!z) {
            i2++;
            SolverVariable pivotCandidate = goal.getPivotCandidate();
            if (pivotCandidate != null) {
                if (!this.mAlreadyTestedCandidates[pivotCandidate.id]) {
                    this.mAlreadyTestedCandidates[pivotCandidate.id] = true;
                    i3++;
                    if (i3 >= this.mNumColumns) {
                        z = true;
                    }
                } else {
                    pivotCandidate = null;
                }
            }
            if (pivotCandidate != null) {
                float f = Float.MAX_VALUE;
                int i4 = -1;
                for (int i5 = 0; i5 < this.mNumRows; i5++) {
                    ArrayRow arrayRow = this.mRows[i5];
                    if (arrayRow.variable.mType != SolverVariable.Type.UNRESTRICTED && arrayRow.hasVariable(pivotCandidate)) {
                        float f2 = arrayRow.variables.get(pivotCandidate);
                        if (f2 < 0.0f) {
                            float f3 = (-arrayRow.constantValue) / f2;
                            if (f3 < f) {
                                i4 = i5;
                                f = f3;
                            }
                        }
                    }
                }
                if (i4 > -1) {
                    ArrayRow arrayRow2 = this.mRows[i4];
                    arrayRow2.variable.definitionId = -1;
                    arrayRow2.pivot(pivotCandidate);
                    arrayRow2.variable.definitionId = i4;
                    for (int i6 = 0; i6 < this.mNumRows; i6++) {
                        this.mRows[i6].updateRowWithEquation(arrayRow2);
                    }
                    goal.updateFromSystem(this);
                    try {
                        enforceBFS(goal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    z = true;
                }
            } else {
                z = true;
            }
        }
        return i2;
    }

    private int enforceBFS(Goal goal) throws Exception {
        boolean z;
        int i;
        int i2 = 0;
        while (true) {
            if (i2 < this.mNumRows) {
                if (this.mRows[i2].variable.mType == SolverVariable.Type.UNRESTRICTED || this.mRows[i2].constantValue >= 0.0f) {
                    i2++;
                } else {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            boolean z2 = false;
            i = 0;
            while (!z2) {
                i++;
                float f = Float.MAX_VALUE;
                int i3 = -1;
                int i4 = -1;
                int i5 = 0;
                for (int i6 = 0; i6 < this.mNumRows; i6++) {
                    ArrayRow arrayRow = this.mRows[i6];
                    if (arrayRow.variable.mType != SolverVariable.Type.UNRESTRICTED && arrayRow.constantValue < 0.0f) {
                        int i7 = i5;
                        float f2 = f;
                        int i8 = i4;
                        int i9 = i3;
                        for (int i10 = 1; i10 < this.mNumColumns; i10++) {
                            SolverVariable solverVariable = this.mCache.mIndexedVariables[i10];
                            float f3 = arrayRow.variables.get(solverVariable);
                            if (f3 > 0.0f) {
                                int i11 = i7;
                                float f4 = f2;
                                int i12 = i8;
                                int i13 = i9;
                                for (int i14 = 0; i14 < 6; i14++) {
                                    float f5 = solverVariable.strengthVector[i14] / f3;
                                    if ((f5 < f4 && i14 == i11) || i14 > i11) {
                                        f4 = f5;
                                        i13 = i6;
                                        i12 = i10;
                                        i11 = i14;
                                    }
                                }
                                i9 = i13;
                                i8 = i12;
                                f2 = f4;
                                i7 = i11;
                            }
                        }
                        i3 = i9;
                        i4 = i8;
                        f = f2;
                        i5 = i7;
                    }
                }
                if (i3 != -1) {
                    ArrayRow arrayRow2 = this.mRows[i3];
                    arrayRow2.variable.definitionId = -1;
                    arrayRow2.pivot(this.mCache.mIndexedVariables[i4]);
                    arrayRow2.variable.definitionId = i3;
                    for (int i15 = 0; i15 < this.mNumRows; i15++) {
                        this.mRows[i15].updateRowWithEquation(arrayRow2);
                    }
                    goal.updateFromSystem(this);
                } else {
                    z2 = true;
                }
            }
        } else {
            i = 0;
        }
        for (int i16 = 0; i16 < this.mNumRows && (this.mRows[i16].variable.mType == SolverVariable.Type.UNRESTRICTED || this.mRows[i16].constantValue >= 0.0f); i16++) {
        }
        return i;
    }

    private void computeValues() {
        for (int i = 0; i < this.mNumRows; i++) {
            ArrayRow arrayRow = this.mRows[i];
            arrayRow.variable.computedValue = arrayRow.constantValue;
        }
    }

    private void displayRows() {
        displaySolverVariables();
        String str = "";
        for (int i = 0; i < this.mNumRows; i++) {
            str = (str + this.mRows[i]) + "\n";
        }
        if (this.mGoal.variables.size() != 0) {
            str = str + this.mGoal + "\n";
        }
        System.out.println(str);
    }

    void displayReadableRows() {
        displaySolverVariables();
        String str = "";
        for (int i = 0; i < this.mNumRows; i++) {
            str = (str + this.mRows[i].toReadableString()) + "\n";
        }
        if (this.mGoal != null) {
            str = str + this.mGoal + "\n";
        }
        System.out.println(str);
    }

    public void displayVariablesReadableRows() {
        displaySolverVariables();
        String str = "";
        for (int i = 0; i < this.mNumRows; i++) {
            if (this.mRows[i].variable.mType == SolverVariable.Type.UNRESTRICTED) {
                str = (str + this.mRows[i].toReadableString()) + "\n";
            }
        }
        if (this.mGoal.variables.size() != 0) {
            str = str + this.mGoal + "\n";
        }
        System.out.println(str);
    }

    public int getMemoryUsed() {
        int i = 0;
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            if (this.mRows[i2] != null) {
                i += this.mRows[i2].sizeInBytes();
            }
        }
        return i;
    }

    public int getNumEquations() {
        return this.mNumRows;
    }

    public int getNumVariables() {
        return this.mVariablesID;
    }

    void displaySystemInformations() {
        int i = 0;
        for (int i2 = 0; i2 < this.TABLE_SIZE; i2++) {
            if (this.mRows[i2] != null) {
                i += this.mRows[i2].sizeInBytes();
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.mNumRows; i4++) {
            if (this.mRows[i4] != null) {
                i3 += this.mRows[i4].sizeInBytes();
            }
        }
        System.out.println("Linear System -> Table size: " + this.TABLE_SIZE + " (" + getDisplaySize(this.TABLE_SIZE * this.TABLE_SIZE) + ") -- row sizes: " + getDisplaySize(i) + ", actual size: " + getDisplaySize(i3) + " rows: " + this.mNumRows + "/" + this.mMaxRows + " cols: " + this.mNumColumns + "/" + this.mMaxColumns + " 0 occupied cells, " + getDisplaySize(0));
    }

    private void displaySolverVariables() {
        String str = "Display Rows (" + this.mNumRows + "x" + this.mNumColumns + ") :\n\t | C | ";
        for (int i = 1; i <= this.mNumColumns; i++) {
            str = (str + this.mCache.mIndexedVariables[i]) + " | ";
        }
        System.out.println(str + "\n");
    }

    private String getDisplaySize(int i) {
        int i2 = i * 4;
        int i3 = i2 / 1024;
        int i4 = i3 / 1024;
        if (i4 > 0) {
            return "" + i4 + " Mb";
        } else if (i3 > 0) {
            return "" + i3 + " Kb";
        } else {
            return "" + i2 + " bytes";
        }
    }

    public Cache getCache() {
        return this.mCache;
    }

    public void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = i2;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        addConstraint(createRow);
    }

    public void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = i2;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        addConstraint(createRow);
    }

    public void addCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, int i3) {
        ArrayRow createRow = createRow();
        createRow.createRowCentering(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        SolverVariable createErrorVariable = createErrorVariable();
        SolverVariable createErrorVariable2 = createErrorVariable();
        createErrorVariable.strength = i3;
        createErrorVariable2.strength = i3;
        createRow.addError(createErrorVariable, createErrorVariable2);
        addConstraint(createRow);
    }

    public ArrayRow addEquality(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        createRow.createRowEquals(solverVariable, solverVariable2, i);
        SolverVariable createErrorVariable = createErrorVariable();
        SolverVariable createErrorVariable2 = createErrorVariable();
        createErrorVariable.strength = i2;
        createErrorVariable2.strength = i2;
        createRow.addError(createErrorVariable, createErrorVariable2);
        addConstraint(createRow);
        return createRow;
    }

    public void addEquality(SolverVariable solverVariable, int i) {
        int i2 = solverVariable.definitionId;
        if (solverVariable.definitionId != -1) {
            ArrayRow arrayRow = this.mRows[i2];
            if (arrayRow.isSimpleDefinition) {
                arrayRow.constantValue = i;
                return;
            }
            ArrayRow createRow = createRow();
            createRow.createRowEquals(solverVariable, i);
            addConstraint(createRow);
            return;
        }
        ArrayRow createRow2 = createRow();
        createRow2.createRowDefinition(solverVariable, i);
        addConstraint(createRow2);
    }

    public static ArrayRow createRowEquals(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowEquals(solverVariable, solverVariable2, i);
        if (z) {
            linearSystem.addSingleError(createRow, 1);
        }
        return createRow;
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, float f, boolean z) {
        ArrayRow createRow = linearSystem.createRow();
        if (z) {
            linearSystem.addError(createRow);
        }
        return createRow.createRowDimensionPercent(solverVariable, solverVariable2, solverVariable3, f);
    }

    public static ArrayRow createRowGreaterThan(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        SolverVariable createSlackVariable = linearSystem.createSlackVariable();
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (z) {
            linearSystem.addSingleError(createRow, (int) ((-1.0f) * createRow.variables.get(createSlackVariable)));
        }
        return createRow;
    }

    public static ArrayRow createRowLowerThan(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        SolverVariable createSlackVariable = linearSystem.createSlackVariable();
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (z) {
            linearSystem.addSingleError(createRow, (int) ((-1.0f) * createRow.variables.get(createSlackVariable)));
        }
        return createRow;
    }

    public static ArrayRow createRowCentering(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, boolean z) {
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowCentering(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        if (z) {
            SolverVariable createErrorVariable = linearSystem.createErrorVariable();
            SolverVariable createErrorVariable2 = linearSystem.createErrorVariable();
            createErrorVariable.strength = 4;
            createErrorVariable2.strength = 4;
            createRow.addError(createErrorVariable, createErrorVariable2);
        }
        return createRow;
    }
}
