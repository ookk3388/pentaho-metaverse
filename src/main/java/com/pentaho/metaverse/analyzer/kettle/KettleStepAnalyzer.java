/*!
 * PENTAHO CORPORATION PROPRIETARY AND CONFIDENTIAL
 *
 * Copyright 2002 - 2014 Pentaho Corporation (Pentaho). All rights reserved.
 *
 * NOTICE: All information including source code contained herein is, and
 * remains the sole property of Pentaho and its licensors. The intellectual
 * and technical concepts contained herein are proprietary and confidential
 * to, and are trade secrets of Pentaho and may be covered by U.S. and foreign
 * patents, or patents in process, and are protected by trade secret and
 * copyright laws. The receipt or possession of this source code and/or related
 * information does not convey or imply any rights to reproduce, disclose or
 * distribute its contents, or to manufacture, use, or sell anything that it
 * may describe, in whole or in part. Any reproduction, modification, distribution,
 * or public display of this information without the express written authorization
 * from Pentaho is strictly prohibited and in violation of applicable laws and
 * international treaties. Access to the source code contained herein is strictly
 * prohibited to anyone except those individuals and entities who have executed
 * confidentiality and non-disclosure agreements or other agreements with Pentaho,
 * explicitly covering such access.
 */

package com.pentaho.metaverse.analyzer.kettle;

import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.platform.api.metaverse.IAnalyzer;
import org.pentaho.platform.api.metaverse.IMetaverseBuilder;
import org.pentaho.platform.api.metaverse.IMetaverseNode;
import org.pentaho.platform.api.metaverse.IMetaverseObjectFactory;
import org.pentaho.platform.api.metaverse.MetaverseAnalyzerException;

/**
 * KettleStepAnalyzer provides a default implementation for analyzing PDI steps to gather metadata for the metaverse.
 */
public class KettleStepAnalyzer implements IAnalyzer<StepMeta> {

  protected IMetaverseBuilder metaverseBuilder;

  protected IMetaverseObjectFactory metaverseObjectFactory;

  /**
   * Analyzes a step to gather metadata (such as input/output fields, used database connections, etc.)
   * 
   * @see org.pentaho.platform.api.metaverse.IAnalyzer#analyze(java.lang.Object)
   */
  @Override
  public IMetaverseNode analyze( StepMeta stepMeta ) throws MetaverseAnalyzerException {

    if ( stepMeta == null ) {
      throw new MetaverseAnalyzerException( "TableOutputMeta is null!" );
    }

    StepMetaInterface stepMetaInterface = stepMeta.getStepMetaInterface();

    if ( stepMetaInterface == null ) {
      throw new MetaverseAnalyzerException( "StepMetaInterface is null!" );
    }

    if ( metaverseObjectFactory == null ) {
      throw new MetaverseAnalyzerException( "MetaverseObjectFactory is null!" );
    }

    // Add yourself
    IMetaverseNode node = metaverseObjectFactory.createNodeObject( "TODO" );

    node.setName( stepMeta.getName() );

    metaverseBuilder.addNode( node );

    DatabaseMeta[] dbs = stepMetaInterface.getUsedDatabaseConnections();
    /*
     * for(DatabaseMeta db : dbs) { getAnalyzer(db).analyze(db); metaverseBuilder.addLink( this, "uses",
     * getIdGenerator(db).getVertex(dbID) ) }
     */

    return node;
  }

  /**
   * Sets the metaverse builder for this analyzer.
   * 
   * @param builder
   *          the new metaverse builder
   * @see org.pentaho.platform.api.metaverse.IAnalyzer#
   *      setMetaverseBuilder(org.pentaho.platform.api.metaverse.IMetaverseBuilder)
   */
  @Override
  public void setMetaverseBuilder( IMetaverseBuilder builder ) {
    this.metaverseBuilder = builder;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.pentaho.platform.api.metaverse.IAnalyzer#setMetaverseObjectFactory(org.pentaho.platform.api.metaverse.
   * IMetaverseObjectFactory)
   */
  @Override
  public void setMetaverseObjectFactory( IMetaverseObjectFactory metaverseObjectFactory ) {
    this.metaverseObjectFactory = metaverseObjectFactory;
  }

}
