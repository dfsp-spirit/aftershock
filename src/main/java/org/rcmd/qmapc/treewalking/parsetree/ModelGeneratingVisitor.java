/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.treewalking.parsetree;

import java.util.Stack;
import org.rcmd.qmapc.ir.model.quakemap.Entity;
import org.rcmd.qmapc.ir.model.quakemap.Brush;
import org.rcmd.qmapc.ir.model.quakemap.QuakeMapModel;
import org.rcmd.qmapc.ir.parsetree.ParseTree;
import org.rcmd.qmapc.ir.parsetree.RuleNode;
import org.rcmd.qmapc.ir.parsetree.TokenNode;
import org.rcmd.qmapc.parsing.parser.Quake2MapParser;

/**
 * A visitor that generates a model while visiting parse tree nodes.
 * @author spirit
 */
public class ModelGeneratingVisitor implements IMapModelGeneratingVisitor, IParseTreeVisitor {
    
    private final QuakeMapModel model;
    private final Stack<RuleNode> ruleNodeStack;
       
    
    public ModelGeneratingVisitor() {
        this.model = new QuakeMapModel();
        this.ruleNodeStack = new Stack<>();
    }

    @Override
    public String visit(TokenNode t) {
        return t.token.text;
    }

    @Override
    public String visit(RuleNode r) {
        ruleNodeStack.add(r);
        
        switch (r.name) {
            case Quake2MapParser.RULE_BRUSH:
                break;
            case Quake2MapParser.RULE_BRUSH_FACE:
                break;
            case Quake2MapParser.RULE_ENTITY:
                return this.visitEntityRuleNode(r);
            case Quake2MapParser.RULE_BRUSH_PATCHDEF:
                break;
            default:
                break;
        }
        
        for(ParseTree p : r.children) {
            if(p instanceof TokenNode) {
                visit((TokenNode)p);
            }
            else if(p instanceof RuleNode) {
                visit((RuleNode)p);
            }
        }
        return r.name;
    }

    @Override
    public QuakeMapModel getMapModel() {
        return this.model;
    }
    
    private String visitEntityRuleNode(RuleNode r) {
        
        Entity e = new Entity();
        this.model.addEntity(e);
        
        e.entityID = this.model.getNextFreeEntityIDInLevel();
        // TODO: configure entity with data from children here
        
        for(ParseTree p : r.children) {
            if(p instanceof TokenNode) {
                System.out.println("visitEntityRuleNode: About to visit token node child: " + ((TokenNode) p).toString());
                visit((TokenNode)p);
            }
            else if(p instanceof RuleNode) {
                System.out.println("visitEntityRuleNode: About to visit rule node child: " + ((RuleNode) p).toString());
                visit((RuleNode)p);
            }
        }
        
        
        return r.name;
    }
    
}