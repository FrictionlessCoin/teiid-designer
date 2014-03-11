/* Generated By:JJTree: Do not edit this line. Function.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.teiid.query.sql.symbol;

import java.util.Arrays;
import org.teiid.designer.query.sql.symbol.IFunction;
import org.teiid.query.function.FunctionDescriptor;
import org.teiid.query.parser.LanguageVisitor;
import org.teiid.query.parser.TeiidNodeFactory.ASTNodes;
import org.teiid.query.parser.TeiidParser;
import org.teiid.query.sql.lang.SimpleNode;

/**
 *
 */
public class Function extends SimpleNode implements Expression, IFunction<FunctionDescriptor, LanguageVisitor> {

    private Class<?> type;

    private String name;

    private Expression[] args;

    private boolean implicit;

    private FunctionDescriptor descriptor;

    /**
     * @param p
     * @param id
     */
    public Function(TeiidParser p, int id) {
        super(p, id);
    }

    /**
     * Get name of function
     * @return Name of function
     */
    @Override
    public String getName() {
        return this.name;
    }
    
    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get function arguments
     * @return Get function arguments
     */
    @Override
    public Expression[] getArgs() {
        return this.args;
    }

    /**
     * Get argument at specified index
     * @param index Index of argument
     * @return argument
     */
    @Override
    public Expression getArg(int index) {
        return this.args[index];
    }

    /**
     * Set the function arguments - it is assumed that the args 
     * are not null.  For no arg functions, use an empty Expression array.
     * @param args Function arguments
     */
    public void setArgs(Expression[] args) {
        if (args == null)
            args = new Expression[0];

        this.args = args;
    }

    /**
     * Get type of function, if known
     * @return Java class name of type, or null if not yet resolved
     */
    @Override
    public Class<?> getType() {
        return this.type;
    }

    /**
     * Set type of function
     * @param type New type
     */
    @Override
    public void setType(Class<?> type) {
        this.type = type;
    }

    /**
     * Make this function implicit / hidden.
     */
    public void makeImplicit() {
        this.implicit = true;
    }

    /**
     * Return true if this function is implicit and should not be shown in SQL representations
     * @return True if implicit
     */
    @Override
    public boolean isImplicit() {
        return this.implicit;
    }

    /**
     * Get the function descriptor that this function resolves to.
     * @return Descriptor or null if resolution has not yet occurred
     */
    @Override
    public FunctionDescriptor getFunctionDescriptor() {
        return this.descriptor;
    }

    /**
     * Set the descriptor for this function.
     * @param fd Function descriptor
     */
    @Override
    public void setFunctionDescriptor(FunctionDescriptor fd) {
        this.descriptor = fd;
    }

    /** 
     * Insert a conversion function at specified index.  This is a convenience 
     * method to insert a conversion into the function tree.
     * @param index Argument index to insert conversion function at
     * @param functionDescriptor Conversion function descriptor
     */
    public void insertConversion(int index, FunctionDescriptor functionDescriptor) { 
        // Get target type for conversion
        Class<?> t = functionDescriptor.getReturnType();
        String typeName = getTeiidParser().getDataTypeService().getDataTypeName(t);
        
        // Pull old expression at index
        Constant constant = getTeiidParser().createASTNode(ASTNodes.CONSTANT);
        constant.setValue(typeName);
        Expression newArg[] = new Expression[] { args[index],  constant};
        
        // Replace old expression with new expression, using old as arg
        Function func = getTeiidParser().createASTNode(ASTNodes.FUNCTION);
        func.setName(functionDescriptor.getName());
        func.setArgs(newArg);
        args[index] = func;
        
        // Set function descriptor and type of new function
        func.setFunctionDescriptor(functionDescriptor);
        func.setType(t);
        func.makeImplicit();
    }

    /**
     * @return true if function is an aggregate
     */
    public boolean isAggregate() {
        return getFunctionDescriptor().getMethod().getAggregateAttributes() != null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.name == null) ? 0 : this.name.toUpperCase().hashCode());
        if(this.args != null && this.args.length > 0 && this.args[0] != null) {
            result = prime * result + Arrays.hashCode(this.args);
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        Function other = (Function)obj;

        if (this.descriptor != null && other.descriptor != null) {
            if (!this.descriptor.getMethod().equals(other.descriptor.getMethod())) {
                return false;
            }
        }

        if (this.getName() == null) {
            if (other.getName() != null) return false;
        } else if (!this.getName().equalsIgnoreCase(other.getName())) return false;

        if (this.implicit != other.implicit) return false;

        if (!Arrays.equals(this.args, other.args)) return false;

        return true;
    }

    /** Accept the visitor. **/
    @Override
    public void acceptVisitor(LanguageVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Function clone() {
        Function clone = new Function(this.parser, this.id);

        if(getArgs() != null) {
            Expression[] cloned = new Expression[getArgs().length];
            for (int i = 0; i < getArgs().length; ++i) {
                cloned[i] = getArgs()[i].clone();
            }
            clone.setArgs(cloned);
        }
        if(getType() != null)
            clone.setType(getType());
        if(getName() != null)
            clone.setName(getName());

        return clone;
    }

}
/* JavaCC - OriginalChecksum=117ee9a033f03357a27a00cd03284aa4 (do not edit this line) */
